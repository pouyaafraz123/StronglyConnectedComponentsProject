package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class NodeCircle {

    @FXML
    private AnchorPane anc;

    @FXML
    private TextField field;

    @FXML
    private Circle circle;

    public AnchorPane getAnc() {
        return anc;
    }

    public TextField getField() {
        return field;
    }

    public Circle getCircle() {
        return circle;
    }
}
