package algorithm;

import controller.Controller;
import javafx.animation.StrokeTransition;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.Edge;
import model.Graph;
import model.Node;
import view.Arrow;

import java.util.ArrayList;
import java.util.Stack;

public class Al {
    public static final Color LINE_COLOR = Color.web("#FF00C4");
    public static final Color CIRCLE_COLOR = Color.web("#FF1053");
    private static Graph graph;

    private static Controller controller;
    private static ArrayList<Integer> group = new ArrayList<>();
    private static void findAns(int v, boolean[] visited) {
        Graph g = Graph.getTranspose(graph);
        visited[v] = true;
        group.add(v+1);

        int n;
        for (Edge edge : g.getVertices().get(v).getEdges()) {
            n = g.getVertices().indexOf(edge.getDestination());
            if (!visited[n])
                findAns(n, visited);
        }
    }


    private static void dfs(int v, boolean[] visited, Stack<Integer> stack) throws InterruptedException {
        visited[v] = true;
        Node node = graph.getVertices().get(v);
        Platform.runLater(() -> controller.appendArea("\t\tDFS("+(node.getName())+")"));
        Platform.runLater(() -> node.getCircle().getCircle().setStroke(CIRCLE_COLOR));
        Thread.sleep(1000);
        Platform.runLater(() -> node.getCircle().getCircle().setStroke(Color.CYAN));

        for (Edge edge : node.getEdges()) {
            Node destination = edge.getDestination();

            int n = graph.getVertices().indexOf(destination);
            if (!visited[n]) {
                Platform.runLater(() -> colorTransition(edge.getLine(), Color.WHITE, LINE_COLOR));
                Thread.sleep(1000);
                Platform.runLater(() -> colorTransition(edge.getLine(), LINE_COLOR,Color.WHITE));
                dfs(n, visited, stack);
            }
        }
        stack.push(v);
    }

    private static void colorTransition(Arrow line,Color from,Color to) {
        line.toFront();
        line.triangle.toFront();
        StrokeTransition transition = new StrokeTransition();
        transition.setFromValue(from);
        transition.setToValue(to);
        transition.setShape(line);
        transition.setDuration(Duration.seconds(0.5));
        transition.play();
        StrokeTransition transition1 = new StrokeTransition();
        transition1.setFromValue(from);
        transition1.setToValue(to);
        transition1.setShape(line.triangle);
        transition1.setDuration(Duration.seconds(1));
        transition1.play();
    }

    public static ArrayList<ArrayList<Integer>> findGroups(Graph g,Controller controller) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        graph = g;
        Al.controller = controller;
        Stack<Integer> stack = new Stack<>();
        int v = graph.getVertices().size();
        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; i++)
            if (!visited[i]) {
                try {
                    dfs(i, visited, stack);
                    controller.appendArea("");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        for (int i = 0; i < v; i++)
            visited[i] = false;
        Integer[] temp = stack.toArray(new Integer[0]);
        Platform.runLater(() -> {
            controller.appendArea("Node Sorted By Finishing Times:");
            for (Integer num : temp){
                controller.appendArea("\t\tNODE{"+num+"}");
            }
        });

        while (!stack.empty()) {
            int p = stack.pop();
            if (!visited[p]) {
                group = new ArrayList<>();
                findAns(p, visited);
                res.add(group);
            }
        }
        return res;
    }
}
