package sample;

import javafx.application.Application; //Importing needed libraries
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CircleDragging extends Application {
    Stage window; //Setting up window
    Scene scene; //Setting up scene

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage; //Setting up stage
        window.setTitle("Drag circle");
        Group GP = new Group(); //Using group layout

        Text AngleOne = new Text(); //Setting up text to track angle size
        Text AngleTwo = new Text();
        Text AngleThree = new Text();

        Circle circ = new Circle(); //Setting up main circle, x & y coordinates, and raius of thr circle
        circ.setCenterX(200.0f);
        circ.setCenterY(200.0f);
        circ.setRadius(90.0f);
        double rad = circ.getRadius(); //Getting radius of main circle
        circ.setFill(Color.TRANSPARENT); //Making main circle transparent
        circ.setStroke(Color.BLACK); //colouring outline of main circle black

        Circle dotsOne = new Circle(); //Setting up small dot with x,y coordinates + radius
        dotsOne.setCenterX(200.0f);
        dotsOne.setCenterY(110.0f);
        dotsOne.setRadius(5.0f);
        dotsOne.setStroke(Color.BLACK);


        Circle dotsTwo = new Circle(); //Setting up small dot with x,y coordinates + radius
        dotsTwo.setCenterX(110.0f);
        dotsTwo.setCenterY(200.0f);
        dotsTwo.setRadius(5.0f);
        dotsTwo.setStroke(Color.BLACK);


        Circle dotsThree = new Circle(); //Setting up small dot with x,y coordinates + radius
        dotsThree.setCenterX(290.0f);
        dotsThree.setCenterY(200.0f);
        dotsThree.setRadius(5.0f);
        dotsThree.setStroke(Color.BLACK);

        // making lines & connecting to respective dots
        Line lineOne = new Line(200.0f,110.0f, 110.0f, 200.0f);
        Line lineTwo = new Line(110.0f,200.0f,290.0f,200.f);
        Line lineThree = new Line(290.0f,200.f,200.0f,110.0f);

        AngleOne.setX(100); //setting angles to be above respective dots
        AngleOne.setY(80);

        AngleTwo.setX(50);
        AngleTwo.setY(200);

        AngleThree.setX(300);
        AngleThree.setY(200);

        ChangeAngle(dotsOne, dotsTwo, dotsThree, AngleOne, AngleTwo, AngleThree); // this function is used to create calculate the angles based on their position


        dotsOne.setOnMouseDragged(e -> { // Mouse event to be able to drag the dot
            double posX = e.getX(); //getting mouse position in x direction
            double posY = e.getY(); //getting mouse position in y direction
            // if statement is wiggle room to be able to move the dot a little bit off circle, otherwise it will be to hard to the dot
            if(rad <= Math.sqrt(Math.pow((200 - posX),2) + Math.pow((200 - posY),2)) + 3 && rad >= Math.sqrt(Math.pow((200 - posX),2) + Math.pow((200 - posY),2)) - 3){
                //connecting line 1 to dotOne
                // Also I know I can Use bind but ive already written it
                dotsOne.setCenterX(posX); //allowing the dot to follow mouse
                dotsOne.setCenterY(posY);
                //Connecting line one start coord to dot one coor
                lineOne.setStartX(posX);
                lineOne.setStartY(posY);
                //Connecting line three start coord to dot three coor
                lineThree.setEndX(posX);
                lineThree.setEndY(posY);
                // setting position of angle text
                AngleOne.setX(posX - 30);
                AngleOne.setY(posY - 30);
                ChangeAngle(dotsOne, dotsTwo, dotsThree, AngleOne, AngleTwo, AngleThree); // this function is used to create calculate the angles based on their position
            }
        });

        dotsTwo.setOnMouseDragged(e -> {
            double posX = e.getX();
            double posY = e.getY();
            // if statement is wiggle room to be able to move the dot a little bit off circle, otherwise it will be to hard to the dot
            if(rad <= Math.sqrt(Math.pow((200 - posX),2) + Math.pow((200 - posY),2)) + 3 && rad >= Math.sqrt(Math.pow((200 - posX),2) + Math.pow((200 - posY),2)) - 3){
                //Connecting lines 1 - 2
                //connecting line 2 to dotTwo
                // Also I know I can Use bind but ive already written it
                dotsTwo.setCenterX(posX); //allowing the dot to follow mouse
                dotsTwo.setCenterY(posY);
                lineOne.setEndX(posX);
                lineOne.setEndY(posY);
                lineTwo.setStartX(posX);
                lineTwo.setStartY(posY);
                // setting position of angle text
                AngleTwo.setX(posX - 30);
                AngleTwo.setY(posY - 30);
                ChangeAngle(dotsOne, dotsTwo, dotsThree, AngleOne, AngleTwo, AngleThree); // this function is used to create calculate the angles based on their position
            }
        });
        dotsThree.setOnMouseDragged(e -> {
            double posX = e.getX();
            double posY = e.getY();
            // if statement is wiggle room to be able to move the dot a little bit off circle, otherwise it will be to hard to the dot
            if(rad <= Math.sqrt(Math.pow((200 - posX),2) + Math.pow((200 - posY),2)) + 3 && rad >= Math.sqrt(Math.pow((200 - posX),2) + Math.pow((200 - posY),2)) - 3){
                dotsThree.setCenterX(posX); //allowing the dot to follow mouse
                dotsThree.setCenterY(posY);
                //Connecting lines 1 - 2
                //connecting line 2 to dotTwo
                // Also I know I can Use bind but ive already written it
                lineThree.setStartX(posX);
                lineThree.setStartY(posY);
                lineTwo.setEndX(posX);
                lineTwo.setEndY(posY);
                // setting position of angle text
                AngleThree.setX(posX - 30);
                AngleThree.setY(posY - 30);
                ChangeAngle(dotsOne, dotsTwo, dotsThree, AngleOne, AngleTwo, AngleThree);
            }
        });

        GP.getChildren().addAll(circ,dotsOne,dotsTwo,dotsThree,lineOne,lineTwo,lineThree,AngleOne,AngleTwo,AngleThree); // adding nodes to layout


        scene = new Scene(GP, 400,400); // creating scene by adding layout and setting GP
        window.setScene(scene); // adding scene to stage
        window.show(); // showing scene
    }
    public void ChangeAngle(Circle dotsOne,Circle dotsTwo, Circle dotsThree, Text lbl1, Text lbl2,Text lbl3) { // calculating angles
        double a = length(dotsOne, dotsTwo); // getting length of line between to dots
        double b = length(dotsTwo, dotsThree);
        double c = length(dotsThree, dotsOne);

        double gamma = Math.toDegrees(Math.acos((a * a - b * b - c * c) / (-2 * b * c))); // calculating angles
        double alpha = Math.toDegrees(Math.acos((b * b - a * a - c * c) / (-2 * a * c)));
        double beta = Math.toDegrees(Math.acos((c * c - b * b - a * a) / (-2 * a * b)));

        lbl1.setText(String.format("%.2f", alpha)); // setting up text to be angles calculated, the %.2f is formatting to 2 decimal places
        lbl2.setText(String.format("%.2f", beta));
        lbl3.setText(String.format("%.2f", gamma));
    }

    private double length(Circle c1, Circle c2) { // using length of line segment eqn to get length of line between two dots

        return Math.sqrt(Math.pow(c1.getCenterX() - c2.getCenterX(), 2) +
                Math.pow(c1.getCenterY() - c2.getCenterY(), 2));
    }
    public static void main(String[] args) { // start function
        launch(args);
    }
}