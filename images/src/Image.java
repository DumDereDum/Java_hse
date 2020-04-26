import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class Image {

    private String filename;
    private int type;
    private long time;
    private int width;
    private int height;
    private int size;
    private BufferedImage image;
    private double lastComparingRes;

    public Image(String path) throws NullPointerException, IOException {
        File file = new File(path);
        this.image = ImageIO.read(file);
        this.filename = file.getName();
        this.type = this.image.getType();
        this.time = file.lastModified();
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.size = this.width * this.height;
        System.out.println("Image " + this.filename + " loaded");
    }

    public Image(File file) throws NullPointerException, IOException {
        this.image = ImageIO.read(file);
        this.filename = file.getName();
        this.type = this.image.getType();
        this.time = file.lastModified();
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.size = this.width * this.height;
        System.out.println("Image " + this.filename + " loaded");
    }

    public Image(BufferedImage img) {
        this.image = img;
        this.filename = "";
        this.type = this.image.getType();
        this.time = 0;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.size = this.width * this.height;
    }

    public long getTime() {
        return time;
    }

    public String getFilename() {
        return filename;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getSize()
    {
        return this.size;
    }

    public int getType()
    {
        return this.type;
    }

    public double getLastComparingRes() {
        return this.lastComparingRes;
    }

    public ArrayList<Integer> getRGB(int x, int y) {
        int javaRGB = this.image.getRGB(x,y);
        ArrayList<Integer> res = new ArrayList<>();
        res.add( (javaRGB >> 16) & 0xFF ); // Red
        res.add( (javaRGB >> 8) & 0xFF );  // Green
        res.add( (javaRGB >> 0) & 0xFF );  // Blue
        return res;
    }

    private int getRGBsum(int x, int y) {
        int sum = 0;
        for (int c : this.getRGB(x, y))
            sum += c;
        return sum;
    }

    public double compareWithImage(Image image){
        double diff = 0;
        double maxRGBsum = 765;

        if (this.getHeight() != image.getHeight() || this.getWidth() != image.getWidth()) {
            try {
                image = getScaledImage(image, this.getWidth(), this.getHeight());
            } catch (IOException e) {
                e.printStackTrace();
                return 0;
            }
        }
        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                diff += (Math.abs(this.getRGBsum(j, i) - image.getRGBsum(j, i))) / maxRGBsum;
            }
        }

        diff /= (this.getHeight() * this.getWidth());
        this.lastComparingRes = 1 - diff;
        return 1 - diff;
    }

    private Image getScaledImage(Image srcImg, int w, int h) throws IOException {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg.image, 0, 0, w, h, null);
        g2.dispose();
        return new Image(resizedImg);
    }

}

