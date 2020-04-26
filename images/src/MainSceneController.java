import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

public class MainSceneController {

    private HashMap<String, ImageCollection> collections = new HashMap<String, ImageCollection>();
    @FXML private TextField folderName;
    @FXML private TextField collectionName;
    @FXML private TextField imageName;
    @FXML private ScrollPane scrollPaneImages;
    @FXML private ScrollPane scrollPaneCollections;

    public MainSceneController() {
    }


    @FXML
    public void buttonClicked() {
        System.out.println("Button clicked!" + folderName.getText());
        Label label1 = new Label("Label1");
        Label label2 = new Label("Label2");
        Label label3 = new Label("Label3");

        FlowPane container = new FlowPane(Orientation.VERTICAL, label1,label2);
        container.getChildren().add(label3);
        scrollPaneImages.setContent(container);
    }

    @FXML
    public void CreateCollection() {
        this.collections.put(collectionName.getText(), new ImageCollection());
        this.printCollections();
    }

    @FXML
    public void DeleteCollection() {
        this.collections.remove(collectionName.getText());
        this.printCollections();
    }


    public void printCollections()
    {
        FlowPane container = new FlowPane(Orientation.VERTICAL);
        for (String collection : this.collections.keySet())
        {
            container.getChildren().add(new Label(collection));
        }
        scrollPaneCollections.setContent(container);
    }

    @FXML
    public void Read()
    {
        try {
            this.collections.get(collectionName.getText()).readImages(folderName.getText());
            FlowPane container = new FlowPane(Orientation.VERTICAL);
            Label label = new Label("images were loaded");
            container.getChildren().add(label);
            scrollPaneImages.setContent(container);
        } catch (NullPointerException e) {
            FlowPane container = new FlowPane(Orientation.VERTICAL);
            Label label = new Label("invalid folder/collection name !");
            container.getChildren().add(label);
            scrollPaneImages.setContent(container);
        }
    }

    void printInvalidCollectionName() {
        FlowPane container = new FlowPane(Orientation.VERTICAL);
        Label label = new Label("invalid collection name !");
        container.getChildren().add(label);
        scrollPaneImages.setContent(container);
    }

    void printCollection() {
        FlowPane container = new FlowPane(Orientation.VERTICAL);
        for (Image image : this.collections.get(collectionName.getText()).getImages()) {
            container.getChildren().add(new Label(image.getFilename()));
        }
        scrollPaneImages.setContent(container);
    }

    @FXML
    public void Print()
    {
        try {
            this.printCollection();
        } catch (NullPointerException e) {
            this.printInvalidCollectionName();
        }

    }

    @FXML
    public void SortByName()
    {
        try {
            this.collections.get(collectionName.getText()).sortByName();
            this.printCollection();
        } catch (NullPointerException e) {
            this.printInvalidCollectionName();
        }
    }

    @FXML
    public void SortByTime()
    {
        try {
            this.collections.get(collectionName.getText()).sortByLastModifiedTime();
            FlowPane container = new FlowPane(Orientation.VERTICAL);
            for (Image image : this.collections.get(collectionName.getText()).getImages()) {
                container.getChildren().add(new Label(image.getFilename() + " " + new Date(image.getTime())));
            }
            scrollPaneImages.setContent(container);
        } catch (NullPointerException e) {
        this.printInvalidCollectionName();
    }

    }

    @FXML
    public void SortByType()
    {
        try {
            this.collections.get(collectionName.getText()).sortByType();
            this.printCollection();
        } catch (NullPointerException e) {
            this.printInvalidCollectionName();
        }
    }

    @FXML
    public void SortBySize()
    {
        try {
            this.collections.get(collectionName.getText()).sortBySize();
            FlowPane container = new FlowPane(Orientation.VERTICAL);
            for (Image image : this.collections.get(collectionName.getText()).getImages() )
            {
                container.getChildren().add(new Label(image.getFilename() + " " + image.getWidth() + "x" + image.getHeight() ));
            }
            scrollPaneImages.setContent(container);
        } catch (NullPointerException e) {
            this.printInvalidCollectionName();
        }

    }

    @FXML
    public void SortBySimilar(){
        try {
            Image img = this.collections.get(collectionName.getText()).getImageByName(imageName.getText());

            this.collections.get(collectionName.getText()).sortBySimilarity(img);
            FlowPane container = new FlowPane(Orientation.VERTICAL);
            for (Image image : this.collections.get(collectionName.getText()).getImages() )
            {
                container.getChildren().add(new Label(image.getFilename() + " " + image.getLastComparingRes() ));
            }
            scrollPaneImages.setContent(container);
        }
        catch (NullPointerException | IOException e) {
            FlowPane container = new FlowPane(Orientation.VERTICAL);
            Label label = new Label("invalid image/collection name !");
            container.getChildren().add(label);
            scrollPaneImages.setContent(container);
        }
    }

}
