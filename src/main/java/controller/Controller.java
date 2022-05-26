package controller;

import algorithm.DFS;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Pair;
import model.Graph;
import model.Node;
import view.Arrow;
import view.NodeCircle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.lang.Math.*;

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
    boolean isLineDrawEnable = false;
    boolean isVertexDrawEnable = false;
    ArrayList<Pair<Node, NodeCircle>> edgeTemp = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addVertex.setOnAction(event -> {
            isVertexDrawEnable = !isVertexDrawEnable;
            if (addVertex.getText().equals("Add Vertex")) {
                container.setOnMouseClicked(event1 -> {
                    if (isVertexDrawEnable)
                        createNewNode(drawCircle(event1.getX(), event1.getY()));
                });
                addVertex.setText("Finished");
            } else {
                addVertex.setText("Add Vertex");
            }
        });

        addEdge.setOnAction(event -> {
            isLineDrawEnable = !isLineDrawEnable;
            if (addEdge.getText().equals("Add Edge"))
                addEdge.setText("Finished");
            else {
                addEdge.setText("Add Edge");
            }
        });
    }

    private NodeCircle drawCircle(double x, double y) {
        try {
            FXMLLoader loader = new FXMLLoader(new File("src/main/resources/Circle.fxml").toURI().toURL());
            container.getChildren().add(loader.load());
            NodeCircle node = loader.getController();
            AnchorPane anc = node.getAnc();
            anc.setLayoutX(x - RADIUS);
            anc.setLayoutY(y - RADIUS);
            anc.setPrefWidth(RADIUS);
            anc.setPrefHeight(RADIUS);

            node.getCircle().setRadius(RADIUS);
            node.getField().setText(String.valueOf(name));

            return node;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private void createNewNode(NodeCircle source) {
        if (graph == null)
            graph = new Graph();
        Node node = new Node(String.valueOf(name), source);
        find.setOnAction(event -> DFS.dfs(Controller.this,node));
        graph.addNode(node);
        Circle circle = source.getCircle();
        circle.setOnMouseEntered(event -> {
            if (isLineDrawEnable) {
                circle.setCursor(Cursor.HAND);
                if (!circle.getFill().equals(Color.web("#00D0FF65")))
                    circle.setFill(Color.web("#00D0FF84"));
            }
        });
        circle.setOnMouseExited(event -> {
            if (isLineDrawEnable) {
                circle.setCursor(Cursor.DEFAULT);
                if (!circle.getFill().equals(Color.web("#00D0FF65")))
                    circle.setFill(Color.web("#00D0FF00"));
            }
        });
        circle.setOnMouseClicked(event -> {
            if (isLineDrawEnable) {
                drawEdge(node, source);
            }
        });
        name++;


    }

    private void drawEdge(Node node, NodeCircle source) {
        source.getCircle().setFill(Color.web("#00D0FF65"));
        if (edgeTemp.size()==1)
            if (edgeTemp.get(0).getKey().getName().equals(node.getName()))
                return;
        edgeTemp.add(new Pair<>(node, source));
        if (edgeTemp.size() == 2) {
            Node node1 = edgeTemp.get(0).getKey();
            Node node2 = edgeTemp.get(1).getKey();
            NodeCircle circle1 = edgeTemp.get(0).getValue();
            NodeCircle circle2 = edgeTemp.get(1).getValue();
            circle1.getCircle().setFill(Color.web("#00D0FF00"));
            circle2.getCircle().setFill(Color.web("#00D0FF00"));

            node1.addEdge(node2);

            double centerX1 = circle1.getAnc().getLayoutX() + RADIUS;
            double centerY1 = circle1.getAnc().getLayoutY() + RADIUS;
            double centerX2 = circle2.getAnc().getLayoutX() + RADIUS;
            double centerY2 = circle2.getAnc().getLayoutY() + RADIUS;
            double startX, startY, endX, endY;

            double R = sqrt(pow((centerX1 - centerX2), 2) + pow(centerY1 - centerY2, 2));
            double yy = (RADIUS * abs(centerY1 - centerY2)) / R;
            double xx = (RADIUS * abs(centerX1 - centerX2)) / R;
            xx *= 1.2;
            yy *= 1.2;

            if (centerX1 < centerX2) {
                startX = centerX1 + xx;
                endX = centerX2 - xx;
            } else {
                startX = centerX1 - xx;
                endX = centerX2 + xx + 6;
            }
            if (centerY1 < centerY2) {
                startY = centerY1 + yy;
                endY = centerY2 - yy;
            } else {
                startY = centerY1 - yy;
                endY = centerY2 + yy + 6;
            }
            Arrow arrow = new Arrow(startX, startY, endX, endY);

            arrow.setStroke(Color.WHITE);

            container.getChildren().add(arrow);
            container.getChildren().add(arrow.triangle);
            //TODO   System.out.println(graph);
            edgeTemp.clear();
        }

    }

    public void onDfs(Node node) {
        area.setText(area.getText()+"DFS("+node.getName()+")\n");
    }
}
