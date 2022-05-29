package algorithm;

import controller.Controller;
import model.Edge;
import model.Graph;

import java.util.ArrayList;
import java.util.Stack;

public class Al {
    private static Graph graph;

 //   Controller controller;
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


    private static void dfs(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;

        for (Edge edge : graph.getVertices().get(v).getEdges()) {
            int n = graph.getVertices().indexOf(edge.getDestination());
            if (!visited[n])
                dfs(n, visited, stack);
        }
        stack.push(v);
    }

    public static ArrayList<ArrayList<Integer>> findGroups(Graph g) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        graph = g;
        Stack<Integer> stack = new Stack<>();
        int v = graph.getVertices().size();
        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; i++)
            visited[i] = false;
        for (int i = 0; i < v; i++)
            if (!visited[i])
                dfs(i, visited, stack);
        for (int i = 0; i < v; i++)
            visited[i] = false;
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
