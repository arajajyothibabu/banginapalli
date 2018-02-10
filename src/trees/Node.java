package trees;

public class Node {

    Integer value;
    Node left, right;

    public Node(Integer value) {
        this.value = value;
        this.left = this.right = null;
    }

    public void add(Node node){
        if(node.value < value){
            if(this.left != null){
                this.left.add(node);
            }else{
                this.left = new Node(node.value);
            }
        }else{
            if(this.right != null){
                this.right.add(node);
            }else{
                this.right = new Node(node.value);
            }
        }
    }

    public void print(){
        System.out.println("( " + value + " )");
    }

}
