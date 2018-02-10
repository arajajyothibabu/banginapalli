package trees;

public class BinaryTree {

    private Node root;

    public Integer size = 0;

    public BinaryTree() { }

    public BinaryTree(Node root) {
        this.root = root;
        this.size++;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node add(Node node){
        root.add(node);
        this.size++;
        return root;
    }

    public void inOrder(Node node){
        if(node != null) {
            inOrder(node.left);
            node.print();
            inOrder(node.right);
        }
    }
    
    public void preOrder(Node node){
        if(node != null) {
            node.print();
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void postOrder(Node node){
        if(node != null) {
            postOrder(node.left);
            postOrder(node.right);
            node.print();
        }
    }

    public Integer height(Node node) {
        //return Math.round(Math.log(size + 1));
        if(node == null){
            return 0;
        }else{
            Integer lHeight = height(node.left);
            Integer rHeight = height(node.right);
            if(lHeight > rHeight) {
                return rHeight + 1;
            } else {
                return lHeight + 1;
            }
        }
    }

    public void levelOrderTraversal(){
        /*if(node != null) {
            System.out.print(index + " :-> ");
            node.print();
            levelOrderTraversal(node.left, index + 1);
            levelOrderTraversal(node.right, index + 1);
        }*/
        for(Integer i = 1; i <= height(root); i++){
            printGivenLevel(root, i);
        }
    }

    private void printGivenLevel(Node node, Integer level){
        if(node == null) return;
        if(level == 1){
            node.print();
        }else if(level > 1){
            printGivenLevel(node.left, level - 1);
            printGivenLevel(node.right, level - 1);
        }
    }

}
