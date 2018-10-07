
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by AS on 07.10.2018.
 */
public class Gui extends Application
{
    TravellingsSalesmanProblem salesmanProblem;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainRoot = new BorderPane();
        mainRoot.setPadding(new Insets(14));
        Pane root = new Pane();
        root.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        //menu at the top
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("File");
        menuBar.getMenus().add(menu);

        MenuItem load = new MenuItem("Load from File");
        MenuItem save = new MenuItem("Save into File");
        MenuItem randomGraph = new MenuItem("Create Random Graph");
        menu.getItems().add(randomGraph);
        menu.getItems().add(save);
        menu.getItems().add(load);

        //Buttonbox at the right
        VBox buttonBox = new VBox(20);
        buttonBox.setPadding(new Insets(20));
        Button start = new Button("Start");
        Button clear = new Button ("Clear");
        ChoiceBox<String> algorithm = new ChoiceBox<>();
        algorithm.getItems().add("Nearest Neighbour");
        algorithm.getItems().add("Brute-Force");
        algorithm.setValue("Nearest Neighbour");
        buttonBox.getChildren().add(algorithm);
        buttonBox.getChildren().add(start);
        buttonBox.getChildren().add(clear);

        //output
        Label label = new Label();
        label.setAlignment(Pos.CENTER);
        label.setPadding(new Insets(0,100,15,100));
        Label label2 = new Label();

        //add all to main
        mainRoot.setCenter(root);
        mainRoot.setBottom(label);
        mainRoot.setRight(buttonBox);
        mainRoot.setTop(menuBar);

        //Used to save a Graph into a XML-File
        save.setOnAction(f ->
        {
            final DirectoryChooser directoryChooser = new DirectoryChooser();
            String directoryPath;

            File file = directoryChooser.showDialog(primaryStage);

            if (file != null)
            {
                directoryPath = file.getAbsolutePath();
            }else{
                directoryPath = System.getProperty("user.home");
            }
            System.out.println(directoryPath);
            WriteXML xml = new WriteXML();

            xml.createXML(salesmanProblem, directoryPath);
        });

        load.setOnAction(h ->
        {
           root.requestFocus();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File(Paths.get("").toAbsolutePath().toString()));
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML-Files", "*. xml"));

            File file = fileChooser.showOpenDialog(primaryStage);

            if (file!=null)
            {
                root.getChildren().clear();
                ReadXML readXML = new ReadXML();
                readXML.readNodes();
            }
        });

        clear.setOnAction(u ->{
            root. getChildren().clear();
            label.setText("");
        });

        //Here you can create a new random Graph.
        randomGraph.setOnAction(e ->
        {
            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10,10,10,10));
            grid.setVgap(10);
            grid.setHgap(10);
            final TextField nodes = new TextField();
            nodes.setPromptText("Amount of Nodes");
            nodes.setPrefColumnCount(10);
            nodes.getText();
            GridPane.setConstraints(nodes, 0,0);
            grid.getChildren().add(nodes);
            final Label l1 = new Label("Nodes");
            GridPane.setConstraints(l1, 1,0);
            grid.getChildren().add(l1);

            Button submit = new Button("Submit");
            GridPane.setConstraints(submit, 0,2);
            grid.getChildren().add(submit);

            Button cancel = new Button("Cancel");
            GridPane.setConstraints(cancel,1,2);
            grid.getChildren().add(cancel);

            Scene secondScene = new Scene(grid, 400,150);

            //New window (stage)
            Stage newWindow = new Stage();
            newWindow.setTitle("Create Randomgraph");
            newWindow.setScene(secondScene);

            submit.setOnAction(a ->
            {
                if (Integer.parseInt(nodes.getText()) <= 0)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Number of Nodes has to be >0! > ");
                    alert.showAndWait();
                }else
                {
                    root.getChildren().clear();
                    label.setText("");
                    salesmanProblem = new TravellingsSalesmanProblem(Integer.parseInt(nodes.getText()));
                    salesmanProblem.fillRandom();
                    ArrayList<Nodes> nodesArrayList = new ArrayList<Nodes>(Arrays.asList(salesmanProblem.nodes));

                }
            });

            //Reset the inputs by the user.
            cancel.setOnAction(c ->
            {
                nodes.clear();
            });
            //Specifies the modality for new Window.
            newWindow.initModality(Modality.WINDOW_MODAL);

            //Specifies the owner Window(parent) for new window
            newWindow.initOwner(primaryStage);

            //Set position of second window, related to primary window.
            newWindow.setX(primaryStage.getX() + 200);
            newWindow.setY(primaryStage.getY() + 100);

            newWindow.show();
        });

        start.setOnAction(s ->
        {
            if (algorithm.getValue() == "Nearest Neighbour");
        });

        Scene scene = new Scene(mainRoot, 1400, 800);

        primaryStage.setTitle("Travelling Salesman Problem");
        primaryStage.setScene(scene);
        primaryStage.show();


    }



    public static void main(String[] args)
    {
        launch(args);
    }
}
