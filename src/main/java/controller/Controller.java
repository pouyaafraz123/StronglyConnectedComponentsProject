package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Graph;
import view.NodeCircle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public static final int RADIUS = 20;
    @FXML
    private JFXButton addVertex;

    @FXML
    private JFXButton find;

    @FXML
    private JFXButton addEdge;

    @FXML
    private JFXTextArea area;

    @FXML
    private AnchorPane container;
    private Graph graph;
    private int name = 1;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addVertex.setOnAction(event -> container.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                createNewNode(drawCircle(event.getX(),event.getY()));
            }
        }));
    }

    private NodeCircle drawCircle(double x, double y) {
        try {
            FXMLLoader loader = new FXMLLoader(new File("src/main/resources/Circle.fxml").toURI().toURL());
            container.getChildren().add(loader.load());
            NodeCircle node = loader.getController();
            AnchorPane anc = node.getAnc();
            anc.setLayoutX(x-RADIUS);
            anc.setLayoutY(y-RADIUS);
            anc.setPrefWidth(RADIUS);
            anc.setPrefHeight(RADIUS);

            node.getCircle().setRadius(RADIUS);
            node.getField().setText(String.valueOf(name));

            return node;
        }catch (IOException ex){
            ex.printStackTrace();
        }

        return null;
    }

    private void createNewNode(NodeCircle source) {
        if (graph==null)
            graph = new Graph();
        graph.addNode(String.valueOf(name),source);
        name++;
        System.out.println(graph);
    }
}
