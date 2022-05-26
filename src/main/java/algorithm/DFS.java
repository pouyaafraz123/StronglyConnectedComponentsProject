package algorithm;

import controller.Controller;
import model.Edge;
import model.Graph;
import model.Node;

import java.util.ArrayList;
import java.util.Stack;

public class DFS {
    private static ArrayList<Node> visited = new ArrayList<>();
    public static ArrayList<Node> dfs(Controller controller,Node start){
        visited = new ArrayList<>();
        ArrayList<Node> answer = new ArrayList<>();
        Stack<Node> nodeStack = new Stack<>();
        nodeStack.push(start);

        while (!nodeStack.isEmpty()){
            Node node = nodeStack.pop();
            answer.add(node);
            controller.onDfs(node);
            visited.add(node);
            for (Edge edge : node.getEdges()){
                if (!visited.contains(edge.getDestination())){
                    nodeStack.push(edge.getDestination());
                }
            }
        }
        return answer;
    }
}
