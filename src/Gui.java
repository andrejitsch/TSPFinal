import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Project TravellingSalesmanProblem This class is used to initiate the GUI
 *
 * @Author Andrej Drobin
 * @Author Deniz Kücüktas
 * @Author Julian Geerdes
 * @Date 28.05.2018
 * @Version 1.1 Last Change: 28.09.2018
 */
public class Gui extends Application
{
    TravellingSalesmanProblem tsp;



    @Override public void start(Stage primaryStage) throws Exception
    {

        BorderPane mainRoot = new BorderPane();
        mainRoot.setPadding(new Insets(14));
        Pane root = new Pane();
        root.setBorder(new Border(
                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                        BorderWidths.DEFAULT)));
        MenuBar menuBar = new MenuBar();
        Menu m = new Menu("File");
        menuBar.getMenus().add(m);
        MenuItem load = new MenuItem("Load from File");
        MenuItem save = new MenuItem("Save Into File");
        MenuItem randomGraph = new MenuItem("Create Random Graph");
        m.getItems().add(load);
        m.getItems().add(save);
        m.getItems().add(randomGraph);
        VBox buttonBox = new VBox(20);
        buttonBox.setPadding(new Insets(20));
        Button start = new Button("Start the Algorithm");
        Button applyChanges = new Button("Apply Changes");
        ChoiceBox<String> algorithm = new ChoiceBox<>();
        algorithm.getItems().add("Nearest Neighbour");
        algorithm.getItems().add("Brute-Force");
        algorithm.setValue("Nearest Neighbour");
        CheckBox cb1 = new CheckBox("Show weights");
        CheckBox cb2 = new CheckBox("Show the names of the Nodes");
        cb1.setSelected(false);
        cb2.setSelected(false);
        buttonBox.getChildren().addAll(algorithm, start, applyChanges, cb1, cb2);
        Label label = new Label();
        label.setAlignment(Pos.CENTER);
        label.setPadding(new Insets(0, 100, 15, 100));
        mainRoot.setCenter(root);
        mainRoot.setBottom(label);
        mainRoot.setRight(buttonBox);
        mainRoot.setTop(menuBar);

        /**
         * Save-Menu: Used to save a Graph into a XML-File
         */

        save.setOnAction(f ->
        {

            final DirectoryChooser directoryChooser = new DirectoryChooser();
            String directoryPath;

            File file = directoryChooser.showDialog(primaryStage);

            if (file != null)
            {
                directoryPath = file.getAbsolutePath();
            } else
            {
                directoryPath = System.getProperty("user.home");
            }
            System.out.println(directoryPath);
            WriteXML xml = new WriteXML();

            xml.createXML(tsp, directoryPath);
        });

        /**
         * Load-Menu: Used to load a Graph through an XML-File
         */

        load.setOnAction(h ->
        {
            root.requestFocus();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File(Paths.get("").toAbsolutePath().toString()));
            fileChooser.getExtensionFilters()
                    .addAll(new FileChooser.ExtensionFilter("XML-Files", "*.xml"));
            File file = fileChooser.showOpenDialog(primaryStage);

            if (file != null)
            {
                root.getChildren().clear();
                ReadXML readXML = new ReadXML();
                ArrayList<Nodes> nodesA = readXML.readNodes(file);
                tsp = new TravellingSalesmanProblem(nodesA.size());
                tsp.convertNodes(nodesA);
                tsp.createEdges();
                tsp.paintGraph(root, cb1.isSelected(), cb2.isSelected());
            }

        });

        /**
         * Clear-Button: Deletes all Nodes and Edges from the Graph
         */

        applyChanges.setOnAction(u ->
        {
            root.getChildren().clear();
            label.setText("");
            tsp.paintGraph(root, cb1.isSelected(), cb2.isSelected());
            tsp.resetTour();
        });

        /**
         * Random-Menu: Creates a random Graph with a number of Nodes and Edges chosen by the User
         */

        randomGraph.setOnAction(e ->
        {

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10, 10, 10, 10));
            grid.setVgap(10);
            grid.setHgap(10);
            final TextField nodes = new TextField();
            nodes.setPromptText("Amount of Nodes");
            nodes.setPrefColumnCount(10);
            nodes.getText();
            GridPane.setConstraints(nodes, 0, 0);
            grid.getChildren().add(nodes);
            final Label l1 = new Label("Nodes");
            GridPane.setConstraints(l1, 1, 0);
            grid.getChildren().add(l1);

            Button submit = new Button("Submit");
            GridPane.setConstraints(submit, 0, 2);
            grid.getChildren().add(submit);

            Button cancel = new Button("Cancel");
            GridPane.setConstraints(cancel, 1, 2);
            grid.getChildren().add(cancel);

            Scene secondScene = new Scene(grid, 400, 150);

            // New window (Stage)
            Stage newWindow = new Stage();
            newWindow.setTitle("Create RandomGraph");
            newWindow.setScene(secondScene);

            /**
             * Submit: Visualizes the Graph that was created randomly
             */

            submit.setOnAction(a ->
            {
                if (Integer.parseInt(nodes.getText()) <= 2)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Number of Nodes has to be >2!");
                    alert.showAndWait();
                } else
                {
                    root.getChildren().clear();
                    label.setText("");
                    tsp = new TravellingSalesmanProblem(Integer.parseInt(nodes.getText()));
                    tsp.fillRandom();
                    tsp.paintGraph(root, cb1.isSelected(), cb2.isSelected());
                    newWindow.close();
                }

            });

            /**
             * Resets the inputs made by the user
             */

            cancel.setOnAction(c ->
            {
                nodes.clear();
            });

            // Specifies the modality for new window.
            newWindow.initModality(Modality.WINDOW_MODAL);

            // Specifies the owner Window (parent) for new window
            newWindow.initOwner(primaryStage);

            // Set position of second window, related to primary window.
            newWindow.setX(primaryStage.getX() + 200);
            newWindow.setY(primaryStage.getY() + 100);

            newWindow.show();
        });

        /**
         * Start-Button: Checks what Algorithm is chosen and starts the chosen Algorithm
         */

        start.setOnAction(s ->
        {
            if (algorithm.getValue() == "Nearest Neighbour")
            {
                Instant startInstant = Instant.now();
                tsp.nearestNeighbour(0);
                int next = 0;
                int x1, y1, x2, y2;

                do
                {

                    x1 = tsp.nodes[next].getXpos();
                    y1 = tsp.nodes[next].getYpos();
                    next = tsp.nodes[next].next;
                    x2 = tsp.nodes[next].getXpos();
                    y2 = tsp.nodes[next].getYpos();
                    Line line = new Line(x1, y1, x2, y2);
                    line.setStroke(Color.RED);
                    line.setStrokeWidth(3);
                    line.toBack();
                    root.getChildren().add(line);
                } while (next != 0);
                label.setText("");
                label.setText(tsp.getTime(startInstant) + " Distance of the Tour: " +
                        Double.toString(tsp.allCost()));

            } else
            {
                Instant startInstant = Instant.now();
                BruteForce bruteForce = new BruteForce();
                Route currentRoute = new Route(new ArrayList<Nodes>(Arrays.asList(tsp.nodes)));
                ArrayList<Route> shortestRoutes =
                        bruteForce.permuteNodes(0, currentRoute, new Route(currentRoute));
                for (int i = 0; i < shortestRoutes.size(); i++)
                {
                    ArrayList<Nodes> shortestRoute = shortestRoutes.get(i).getNodes();
                    for (int j = 0; j < shortestRoute.size() - 1; j++)
                    {
                        int count = 1;
                        Line lineBruteForce = new Line(shortestRoute.get(j).getXpos(),
                                shortestRoute.get(j).getYpos(),
                                shortestRoute.get(j + count).getXpos(),
                                shortestRoute.get(j + count).getYpos());
                        Line lineLastToFirstNode =
                                new Line(shortestRoute.get(shortestRoute.size() - 1).getXpos(),
                                        shortestRoute.get(shortestRoute.size() - 1).getYpos(),
                                        shortestRoute.get(0).getXpos(),
                                        shortestRoute.get(0).getYpos());
                        lineBruteForce.setStrokeWidth(3);
                        lineLastToFirstNode.setStrokeWidth(3);
                        lineBruteForce.setStroke(Color.GREEN);
                        lineLastToFirstNode.setStroke(Color.GREEN);
                        lineBruteForce.toBack();
                        lineLastToFirstNode.toBack();
                        root.getChildren().addAll(lineBruteForce, lineLastToFirstNode);

                    }
                }

                String distance = bruteForce.getTotalDistance(shortestRoutes.get(0));

                label.setText("");
                label.setText(
                        tsp.getTime(startInstant) + " Distance of the shortest Route: " + distance);
            }

        });

        Scene scene = new Scene(mainRoot, 1400, 800);

        primaryStage.setTitle("Traveling Salesman Problem");
        primaryStage.setScene(scene);
        scene.getStylesheets().add("design2.css");
        primaryStage.show();
    }



    /**
     * @param args
     */

    public static void main(String[] args)
    {
        launch(args);
    }
}