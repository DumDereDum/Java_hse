import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ImageCollection {

    private final ArrayList<Image> images;

    public ImageCollection()
    {
        this.images = new ArrayList<Image>();
    }

    public void readImages(String path) throws NullPointerException {
        File folder = new File(path);
        File[] folderEntries = folder.listFiles();
        for (File entry : folderEntries)
        {
            try {
                this.images.add(new Image(entry));
            } catch (NullPointerException | IOException e) {
                System.out.println("File " + entry.getName() + " is not image");
            }
        }
    }

    public void printImages()
    {
        for (Image image : this.images)
        {
            System.out.println(image.getFilename());
        }
    }

    public ArrayList<Image> getImages()
    {
        return this.images;
    }

    public Image getImageByName(String imageName) throws IOException
    {
        for (Image image : this.images)
        {
            if (image.getFilename().equals(imageName))
            {
                return image;
            }
        }
        throw new IOException();
    }

    public void sortByName()
    {
        this.images.sort(Comparator.comparing(image -> image.getFilename()));
    }

    public void sortByLastModifiedTime()
    {
        this.images.sort(Comparator.comparing(image -> image.getTime()));
    }

    public void sortByType()
    {
        this.images.sort(Comparator.comparing(image -> image.getType()));
    }

    public void sortBySize()
    {
        this.images.sort(Comparator.comparing(image -> image.getSize()));
    }

    public void sortBySimilarity(Image image)
    {
        for (Image img : this.images)
        {
            img.compareWithImage(image);
        }
        this.images.sort(Comparator.comparingDouble(Image::getLastComparingRes));
        Collections.reverse(this.images);
    }

}
