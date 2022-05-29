package model;

import view.NodeCircle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node {
    private String name;
    private final List<Edge> edges = Collections.synchronizedList(new ArrayList<>());
    private NodeCircle nodeCircle;

    public Node(String name) {
        this.name = name;
    }

    public Node(String name, NodeCircle nodeCircle) {
        this.name = name;
        this.nodeCircle = nodeCircle;
    }

    public void addEdge(Node node){
        Edge edge = new Edge(this,node);
        this.edges.add(edge);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NodeCircle getCircle() {
        return nodeCircle;
    }

    public void setCircle(NodeCircle nodeCircle) {
        this.nodeCircle = nodeCircle;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", edges=" + edges +
                ", nodeCircle=" + nodeCircle +
                '}';
    }
}
