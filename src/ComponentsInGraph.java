import java.util.ArrayList;
import java.util.Scanner;

class Node {

    public Integer value;
    public ArrayList<Node> children;

    public Node(Integer value, ArrayList<Node> children) {
        this.value = value;
        this.children = children;
    }

    public boolean contains(Integer value) {
        if(this.value.equals(value)){
            return true;
        }else{
            for(Node node : children){
                return node.contains(value);
            }
        }
        return false;
    }

    public Node add(Integer start, Integer end){
        if(value.equals(start)){
            children.add(new Node(end, new ArrayList<Node>()));
        }else if(value.equals(end)){
            children.add(new Node(start, new ArrayList<Node>()));
        }else{
            for(Node child: children){
                child.add(start, end);
            }
        }
        return this;
    }

    public Node add(Node node){
        children.add(node);
        return this;
    }

    public Node addChildren(Node node){
        children.add(node);
        return this;
    }

    public void print(){
        System.out.println("Value: " + value + " >> " + (vertices().size() - 1));
        for(Node child: children){
            System.out.println("^");
            child.print();
        }
    }

    public ArrayList<Integer> vertices(){
        ArrayList<Integer> values = new ArrayList<Integer>();
        values.add(this.value);
        for(Node child: children){
            for(Integer value : child.vertices()){
                if(!value.equals(this.value)) {
                    values.add(value);
                }
            }
        }
        return values;
    }

    public boolean isSubNode(Node node) {
        ArrayList<Integer> vertices = vertices();
        for (Integer value : node.vertices()) {
            if(vertices.contains(value)){
                return true;
            }
        }
        return false;
    }

}

public class ComponentsInGraph {

    private Integer N;
    private ArrayList<Node> roots;

    public ComponentsInGraph() {
        roots = new ArrayList<Node>();
    }

    private void readInput() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for(int i = 0; i < N; i++){
            Integer start = sc.nextInt(), end = sc.nextInt();
            if(roots.size() > 0){
                for(Node root : roots){
                    if(root.contains(start) || root.contains(end)){
                        root.add(start, end);
                        break;
                    }
                }
                ArrayList<Node> children = new ArrayList<Node>();
                children.add(new Node(end, new ArrayList<Node>()));
                roots.add(new Node(start, children));
            }else{
                ArrayList<Node> children = new ArrayList<Node>();
                children.add(new Node(end, new ArrayList<Node>()));
                roots.add(new Node(start, children));
            }
        }
        for(Integer i = 0; i < roots.size(); i++){
            for(Integer j = i + 1; j < roots.size(); j++){
                if(roots.get(i).isSubNode(roots.get(j))){
                    roots.get(i).add(roots.get(j));
                    roots.remove(j);
                }
            }
        }
    }

    private void printRoots(){
        System.out.println(roots.size());
        for(Node root: roots){
            root.print();
        }
    }

    public void compute(){
        readInput();
        printRoots();
    }

}
