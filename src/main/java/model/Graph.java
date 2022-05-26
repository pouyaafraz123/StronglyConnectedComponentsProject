package model;

import view.NodeCircle;

import java.security.SecureRandom;
import java.util.ArrayList;

public class Graph {
    private int size;
    private final ArrayList<Node> vertices = new ArrayList<>();

    public Graph(){}

    public Graph(int size){
        this.size = size;
        createRandomGraph(size);
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

    public void addNode(String name, NodeCircle circle){
        vertices.add(new Node(name,circle));
        size++;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "vertices=" + vertices +
                '}';
    }
}
