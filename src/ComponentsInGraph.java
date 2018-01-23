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

    public ArrayList<Integer> connectedNodes(){
        ArrayList<Integer> nodes = new ArrayList<Integer>();
        nodes.add(label);
        for(Node child : adjacencyList){
            for(Integer label: child.connectedNodes()){
                if(!nodes.contains(label)) {
                    nodes.add(label);
                }
            }
        }
        return nodes;
    }

}

class Graph extends HashMap<Integer, Node>{

    public Graph() {}

    public void add(Edge edge){
        Integer start = edge.start, end = edge.end;
        Node endNode;
        if(this.containsKey(end)){
            endNode = this.get(end);
        }else{
            endNode = new Node(end, new ArrayList<Node>());
        }
        if(this.containsKey(start)){
            this.get(start).adjacencyList.add(endNode);
        }else{
            ArrayList<Node> children = new ArrayList<Node>();
            children.add(endNode);
            this.put(start, new Node(start, children));
        }
    }

    public void print(){
        System.out.println("Size: " + this.size());
        for(Integer key : this.keySet()){
            this.get(key).print();
        }
    }

}

class Edge {

    public Integer start, end;

    public Edge(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }
}

public class ComponentsInGraph {

    private Integer N;
    private Graph graph;

    public ComponentsInGraph() {
        graph = new Graph();
    }

    private void constructGraph() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for(int i = 0; i < N; i++){
            Integer start = sc.nextInt(), end = sc.nextInt();
            graph.add(new Edge(start, end));
        }
    }

    private ArrayList<Integer> intersection(ArrayList<Integer> a, ArrayList<Integer> b){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(Integer i : a){
            if(b.contains(i)){
                list.add(i);
            }
        }
        return list;
    }

    private ArrayList<Integer> union(ArrayList<Integer> a, ArrayList<Integer> b){
        for(Integer i : a){
            if(!b.contains(i)){
                b.add(i);
            }
        }
        return b;
    }

    public void compute(){
        constructGraph();
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        if(graph.size() > 0){
            //graph.print();
            ArrayList<Integer> connected;
            for(Integer key : graph.keySet()) {
                connected = graph.get(key).connectedNodes();
                list.add(connected);
            }
            for(Integer i = 0; i < list.size(); i++){
                for(Integer j = i + 1; j < list.size(); j++){
                    if(intersection(list.get(i), list.get(j)).size() > 0){
                        list.set(i, union(list.get(i), list.get(j)));
                    }
                }
            }
            Integer min = 2, max = 0;
            for(ArrayList<Integer> nodes : list){
                Integer vertices = nodes.size();
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
