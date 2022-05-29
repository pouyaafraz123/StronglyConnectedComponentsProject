package model;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    private int size;
    private final ArrayList<Node> vertices = new ArrayList<>();

    public Graph(){}

    public Graph(int size){
        this.size = size;
        createRandomGraph(size);
    }

    public ArrayList<Node> getVertices() {
        return vertices;
    }

    public static Graph getTranspose(Graph g){
        Graph graph = new Graph();
        for (Node node : g.getVertices()){
            graph.getVertices().add(new Node(node.getName(), node.getCircle()));
        }
        for (Node node : g.getVertices()){
            for (Edge edge : node.getEdges()){
                ArrayList<Node> nodes = graph.getVertices();
                nodes.get(g.getVertices().indexOf(edge.getDestination())).addEdge(nodes
                        .get(g.getVertices().indexOf(edge.getSource())));
            }
        }
        return graph;
    }

    private void createRandomGraph(int size) {
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < size; i++) {
            vertices.add(new Node(String.valueOf(i+1)));
        }

        for (int i = 0;i < size;i++){
            for (int j = 0; j < random.nextInt(size-1)+1; j++) {
                Node node = vertices.get(i);
                int rand = getExRand(i);
                node.addEdge(vertices.get(rand));
            }
        }
    }

    private int getExRand(int i) {
        SecureRandom random = new SecureRandom();
        int rand = random.nextInt(size);
        if (i==rand)
            return getExRand(i);
        else return rand;
    }

    public void addNode(Node node){
        vertices.add(node);
        size++;
    }

    public void addNodes(Node... nodes){
        vertices.addAll(Arrays.asList(nodes));
        size+=nodes.length;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "vertices=" + vertices +
                '}';
    }
}
