import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {



        ImageCollection images = new ImageCollection();
        images.readImages("images/");
        System.out.println("--");
        images.printImages();
        System.out.println("--");
        //images.sortByName();
        //images.sortBySize();
        //images.sortByLastModifiedTime();
        //images.sortByType();
        Image image = new Image("image1.jpg");
        images.sortBySimilarity(image);
        images.printImages();


        Image image1 = new Image("image1.jpg");
        Image image2 = new Image("image2.jpg");
        Image image3 = new Image("image3.png");
        Image image4 = new Image("image4.jpg");
        Image image5 = new Image("image5.jpg");
        Image image6 = new Image("image6.jpg");
        Image image7 = new Image("image7.jpg");
        System.out.println(image1.getHeight() + "==" +  image2.getHeight());

        double res = image1.compareWithImage(image2);
        System.out.println(res);
        res = image1.compareWithImage(image3);
        System.out.println(res);
        res = image1.compareWithImage(image4);
        System.out.println(res);
        res = image1.compareWithImage(image5);
        System.out.println(res);
        res = image1.compareWithImage(image6);
        System.out.println(res);
        res = image1.compareWithImage(image7);
        System.out.println(res);

    }

}
