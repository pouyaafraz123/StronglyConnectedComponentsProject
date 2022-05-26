package model;

import view.Arrow;

public class Edge {
    private Node source;
    private Node destination;
    private Arrow arrow;

    public Edge(Node source, Node destination) {
        this.source = source;
        this.destination = destination;
    }

    public Node getSource() {
        return source;
    }

    public void setSource(Node source) {
        this.source = source;
    }

    public Node getDestination() {
        return destination;
    }

    public void setDestination(Node destination) {
        this.destination = destination;
    }

    public Arrow getLine() {
        return arrow;
    }

    public void setLine(Arrow arrow) {
        this.arrow = arrow;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "source=" + source.getName() +
                ", destination=" + destination.getName() +
                '}';
    }
}
