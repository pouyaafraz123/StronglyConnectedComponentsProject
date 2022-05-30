package view;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

public class Arrow extends javafx.scene.shape.Line {
    public Polygon triangle;

    public Arrow(double startX, double startY, double endX, double endY) {
        super(startX, startY, endX, endY);

        this.setStroke(Color.WHITE);
        this.setStrokeWidth(1);
        this.setSmooth(true);
        this.setStrokeLineCap(StrokeLineCap.ROUND);
        this.setStrokeLineJoin(StrokeLineJoin.ROUND);

        double dx = endX - startX;
        double dy = endY - startY;
        double angle = Math.atan2(dy, dx);
        triangle = new Polygon(endX, endY, endX - 8, endY + 4, endX - 8, endY - 4);
        triangle.setStroke(Color.WHITE);
        triangle.setFill(Color.WHITE);
        triangle.setStrokeWidth(4);
        triangle.setRotate(Math.toDegrees(angle));
        triangle.setRotationAxis(new Point3D(0,0,1));
     /*   triangle.rotateProperty().bind(Bindings.createDoubleBinding(() -> {
            double x = this.getEndX() - this.getStartX();
            double y = this.getEndY() - this.getStartY();
            double a = Math.atan2(y, x);
            return Math.toDegrees(a);
        }));*/
    }

    public void setColor(Color color){
        triangle.setFill(color);
        this.setStroke(color);
    }
}
