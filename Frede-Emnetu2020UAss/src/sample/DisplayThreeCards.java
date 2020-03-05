package sample;


import javafx.application.Application; //importing libraries
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.Random;


public class DisplayThreeCards extends Application {
    Stage window; //Naming stage
    Scene scene; // Naming scene
    Image img1; // Creating images
    Image img2;
    Image img3;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage; //Setting up window
        window.setTitle("Display Three Cards");

        Random rand = new Random(); //Random variable

        int num1 = rand.nextInt((54 - 1) + 1) + 1; //Setting up random numbers to get random images
        int num2 = rand.nextInt((54 - 1) + 1) + 1;
        int num3 = rand.nextInt((54 - 1) + 1) + 1;

        img1 = new Image(DisplayThreeCards.class.getResourceAsStream("./Cards/"+ num1 +".png")); //Accessing images using random numbers
        img2 = new Image(DisplayThreeCards.class.getResourceAsStream("./Cards/"+ num2 +".png"));
        img3 = new Image(DisplayThreeCards.class.getResourceAsStream("./Cards/"+ num3 +".png"));

        ImageView IVOne = new ImageView(img1); //Creating image views of images found using random numbers
        ImageView IVTwo = new ImageView(img2);
        ImageView IVThree = new ImageView(img3);

        HBox hbox = new HBox(IVOne,IVTwo,IVThree); //Adding image views to hBox

        scene = new Scene(hbox); //Creating scene
        window.setScene(scene); //Adding scene to stage (window)
        window.show(); //Showing window

    }
    public static void main(String[] args) {
        launch(args);
    }
}
