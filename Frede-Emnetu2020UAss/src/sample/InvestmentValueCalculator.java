package sample;

import javafx.application.Application; //importing needed libraries
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InvestmentValueCalculator extends Application {
    Stage window; //setting window
    Scene scene; //setting stage
    @Override
    public void start(Stage primaryStage){
        window = primaryStage;
        window.setTitle("Investment-Value Calculator: ");

        GridPane grid = new GridPane(); // creating layoit
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10); // setting up respective gaps
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25)); //padding

        Label InvestmentAmount = new Label("Investment Amount: "); // creating label for Investment Amount
        grid.add(InvestmentAmount,0,0); // adding above node to grid

        TextField IA =  new TextField(); // creating text field for user to input data
        grid.add(IA,1,0);  // adding above node to grid

        Label Years = new Label("Years: "); // creating label for Years
        grid.add(Years,0,1); // adding above node to grid

        TextField Yrs = new TextField(); // creating text field for user to input data
        grid.add(Yrs,1,1); // adding above node to grid

        Label AnnualInterestAmount = new Label("Annual Interest Amount: "); // creating label for Annual Intrest Amnount
        grid.add(AnnualInterestAmount,0,2); // adding above node to grid

        TextField AIA = new TextField(); // creating text field for user to input data
        grid.add(AIA,1,2); // adding above node to grid

        Label FutureValue = new Label("Future Value: "); // creating label for Future Value
        grid.add(FutureValue,0,3); // adding above node to grid

        TextField FV = new TextField(); // creating text field for user to input data
        FV.setDisable(true); // making sure user cant edit calculation
        FV.setEditable(false);
        grid.add(FV,1,3); // adding above node to grid

        Button btn = new Button("Calculate"); // creating calculating button
        grid.add(btn,1,4); // adding above node to grid

        btn.setOnAction(e -> { // Event when button is pressed calculation will be done
            double InvAmount = Double.parseDouble(IA.getText());// getting values from the respective text fields
            double yrs = Double.parseDouble(Yrs.getText());
            double AIAm = (Double.parseDouble(AIA.getText())/100)/12;  // converting annual amount to monthly, 100 is for converting percent to number
            double IDK = Math.pow(1 + AIAm, yrs*12); // calculating future value
            double FVe = InvAmount*IDK;
            FV.setText(String.valueOf(FVe)); // puttinng calculation in future value text field

        });

        scene = new Scene(grid); // adding grid to scene
        window.setScene(scene); // adding scene to stage
        window.show(); // showing stage

    }

    public static void main(String[] args) {
        launch(args);
    }
}