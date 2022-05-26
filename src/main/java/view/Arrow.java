package view;

import javafx.beans.binding.Bindings;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Arrow extends javafx.scene.shape.Line {
    public Polygon triangle;

    public Arrow(double startX, double startY, double endX, double endY) {
        super(startX, startY, endX, endY);
        this.setStroke(Color.WHITE);
        this.setStrokeWidth(3);
        this.setSmooth(true);
        double dx = endX - startX;
        double dy = endY - startY;
        double angle = Math.atan2(dy, dx);
        triangle = new Polygon(endX, endY, endX - 8, endY + 4, endX - 8, endY - 4);
        triangle.setStroke(Color.WHITE);
        triangle.setFill(Color.WHITE);
        triangle.setStrokeWidth(5);
        triangle.setRotate(Math.toDegrees(angle));
        triangle.rotateProperty().bind(Bindings.createDoubleBinding(() -> {
            double x = this.getEndX() - this.getStartX();
            double y = this.getEndY() - this.getStartY();
            double a = Math.atan2(y, x);
            return Math.toDegrees(a);
        }));
    }
}
