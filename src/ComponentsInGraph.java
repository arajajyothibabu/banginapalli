import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Node {

    public Integer label;
    public ArrayList<Node> adjacencyList;

    public Node(Integer label, ArrayList<Node> adjacencyList) {
        this.label = label;
        this.adjacencyList = adjacencyList;
    }

    public boolean contains(Integer value) {
        if(this.label.equals(value)){
            return true;
        }else{
            for(Node node : adjacencyList){
                return node.contains(value);
            }
        }
        return false;
    }

    public void print(){
        System.out.println("Label: " + label);
        System.out.println("^^^^^^^^^^^^^^^^^^^^^");
        for(Node child: adjacencyList){
            System.out.print(child.label + "\t");
        }
        System.out.println("\n^^^^^^^^^^^^^^^^^^^^^\n");
    }

}

public class ComponentsInGraph {

    private Integer N;
    private HashMap<Integer, Node> graph;

    public ComponentsInGraph() {
        graph = new HashMap<Integer, Node>();
    }

    private void constructGraph() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for(int i = 0; i < N; i++){
            Integer start = sc.nextInt(), end = sc.nextInt();
            Node endNode;
            if(graph.containsKey(end)){
                endNode = graph.get(end);
            }else{
                endNode = new Node(end, new ArrayList<Node>());
            }
            if(graph.containsKey(start)){
                graph.get(start).adjacencyList.add(endNode);
            }else{
                ArrayList<Node> children = new ArrayList<Node>();
                children.add(endNode);
                graph.put(start, new Node(start, children));
            }
        }
    }

    private void printGraph(){
        System.out.println("Size: " + graph.size());
        for(Integer key : graph.keySet()){
            graph.get(key).print();
        }
    }

    public void compute(){
        constructGraph();
        if(graph.size() > 0){
            //printGraph();
            Integer min = 2, max = 0;
            for(Integer key : graph.keySet()){
                Integer vertices = graph.get(key).adjacencyList.size() + 1;
                if(max < vertices){
                    max = vertices;
                }
                if(min > vertices){
                    min = vertices;
                }
            }
            System.out.println(min + " " + max);
        }else{
            System.out.println(0 + " " + 0);
        }
    }

}
