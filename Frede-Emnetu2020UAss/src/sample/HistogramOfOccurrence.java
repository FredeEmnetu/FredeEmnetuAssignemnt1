package sample;

// importing needed libraries
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.*;
import java.util.Arrays;


public class HistogramOfOccurrence extends Application {
    Scene scene; //setting scene
    Stage window; //setting window
    public void start (Stage primaryStage) throws IOException {
        window = primaryStage;
        TextField path = new TextField();
        path.setPromptText("Please enter absolute path");

        Button btn = new Button("Enter"); //adding interactive enter button
        HBox hBox = new HBox(path, btn); // creating HBox layout
        hBox.setSpacing(12);

//        String filename = "C:/Users/ferid/Document/Frede-Emnetu2020UAss/src/sample/README.txt";
        hBox.setAlignment(Pos.CENTER); //aligning text box to centre of bottom borderBox


        CategoryAxis xAxis = new CategoryAxis(); //adding x-axis and labels
        xAxis.setCategories(FXCollections.<String>
                observableArrayList(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z")));
        xAxis.setLabel("Letters");

        NumberAxis yAxis = new NumberAxis(); //adding y-axis and label
        yAxis.setLabel("Occurrence");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

        btn.setOnAction(e -> { // on click the graph will be produced
            try { // try anc catch block
                String file = path.getText(); // getting path fron text field
                createHisto(barChart, file); // function to create histogram
            } catch (Exception error) { // catch to get error
                System.out.println("Error has occurred");
            }
        });



        //Creating a Group object
        BorderPane bp = new BorderPane(); // creating border pane layout
        bp.setCenter(barChart); // adding nodes
        bp.setBottom(hBox);
        BorderPane.setMargin(hBox, new Insets(12, 10, 12, 12)); // adding spacing between text field and btn


        scene = new Scene(bp, 600, 400);

        //Setting title to the Stage
        window.setTitle("Bar Chart");

        //Adding scene to the stage
        window.setScene(scene);

        //Displaying the contents of the stage
        window.show();



    }
    public static void main (String[]args){
        launch(args);
    }
        private void createHisto(BarChart barChart, String path) { //create histogram function
            int[] arr = new int[26]; // creating to keep track of number of occurences
            Arrays.fill(arr, 0); // setting arr to be filled with zeros
            try { // try and catch block
                File file = new File(path); // setting the file to path to a File Object
                BufferedReader br = new BufferedReader(new FileReader(file)); // using BufferReader to read file
                String strline; // string to keep track of line

                while ((strline = br.readLine()) != null) { // while loop to read file, condition is making sure line isnt empty
                    // I ran into a bug where BufferReader stopped as soon as it reached a space
                    // so I took the line bufferReader got and removed the spaces
                    char [] CHArr = new char[strline.length()];  // array to put string into character array

                    strline = strline.toLowerCase(); // converting bufferReader line to lower case
                    StringBuilder fixbug= new StringBuilder(); //String that I used to remove spaces
                    for(char letter: strline.toCharArray()) { // putting strline characters into fixbug variable
                        if (letter != (' ')) { // making sure character is not a space
                            fixbug.append(letter); // adding character to fix bug
                        }
                    }

                    for(int x = 0; x < fixbug.length(); x++) { // adding all fixbug characters to Char array
                        CHArr[x] = fixbug.charAt(x);
                    }

                    for (char c : CHArr) { // putting the letter into the respectve index of array. Example if the letter is 'C', will go into 3rd index
                        int convert = Math.abs(Character.getNumericValue(c) - Character.getNumericValue('a'));
                            arr[convert]++; // adding number of occurrence

                    }

                }
                br.close(); // closing buffer reader
            } catch (Exception f) { // catch statement to catch an error, in case of an error
                System.err.println(f);
            }

            //Creating the Bar chart

            barChart.setTitle("Occurrence of letters in file"); // title of chart
            //Prepare XYChart.Series objects by setting data
            XYChart.Series<String, Number> series1 = new XYChart.Series<>(); // creating series
            int letter = 97;
            for (int x = 0; x < 26; x++) { // adding number of occurence of letter to the series
                Integer iInteger = arr[x];
                series1.getData().add(new XYChart.Data<>(String.valueOf((char) letter), iInteger));
                letter++;
            }
            barChart.getData().add(series1); // adding series to barchart
        }
}



