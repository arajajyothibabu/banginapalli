package trees.lib;

public class Node {

    Integer value;
    Node left, right;

    public Node(Integer value) {
        this.value = value;
        this.left = this.right = null;
    }

    /*public Node add(Node node){
        if(node.value < value){
            this.left = node;
        }else{
            this.right = node;
        }
        return node;
    }*/

    public Boolean hasLeft(){
        return left != null;
    }

    public Boolean hasRight(){
        return right != null;
    }

    public void print(){
        System.out.println("( " + value + " )");
    }

}
