package trees.lib;

import java.util.NoSuchElementException;

public class BinaryTree extends Exception {

    private Node root;

    public Integer size = 0;

    public BinaryTree() { }

    public BinaryTree(Node root) {
        this.root = root;
        this.size++;
    }

    public Boolean isEmpty(){
        return root == null;
    }

    public Boolean contains(Integer value){
        return contains(root, value);
    }

    private Boolean contains(Node node, Integer value){
        if(node != null){
            if(node.value.equals(value)){
                return true;
            }else{
                return node.value > value ? contains(node.left, value) : contains(node.right, value);
            }
        }
        return false;
    }

    private Node find(Node node, Integer value){
        if(node != null){
            if(node.value.equals(value)){
                return node;
            }else{
                return node.value > value ? find(node.left, value) : find(node.right, value);
            }
        }
        return null;
    }

    public void insert(Node node){
        root = insert(root, node);
    }

    public void delete(Integer value){
        root = delete(root, value);
    }

    private Integer findMin(Node node){
        return node.left == null ? node.value : findMin(node.left);
    }

    private Integer findMax(Node node){
        return node.right == null ? node.value : findMin(node.right);
    }

    public Integer min() {
        if(isEmpty()){
            throw new NoSuchElementException("Tree is Empty..!");
        }else{
            return findMin(root);
        }
    }

    public Integer max(){
        if(isEmpty()){
            throw new NoSuchElementException("Tree is Empty..!");
        }else{
            return findMax(root);
        }
    }

    private Node delete(Node node, Integer value){
        if(node == null){
            return null;
        }else if(node.value.equals(value)){
            if(!node.hasLeft() && !node.hasRight()){
                return null;
            }else if(node.hasLeft()){
                return node.left;
            }else if(node.hasRight()){
                return node.right;
            }else{
                Integer smallestInRightSubTree = findMin(node.right);
                node.value = smallestInRightSubTree;
                node.right = delete(node.right, smallestInRightSubTree);
            }
            this.size--; //decrementing tree size
        }else if(node.value > value){
            node.left = delete(node.left, value);
        }else{
            node.right = delete(node.right, value);
        }
        return node;
    }

    private Node insert(Node node, Node newNode){
        if(node == null){
            node = newNode;
            this.size++; //incrementing tree size
        }else if(node.value < newNode.value){
            node.left = insert(node.left, newNode);
        }else if(node.value < newNode.value){
            node.right = insert(node.right, newNode);
        }
        //Leaves if value equals this node's value
        return node;
    }

    /**
     * Traversals
     */

    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node){
        if(node != null) {
            inOrder(node.left);
            node.print();
            inOrder(node.right);
        }
    }

    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node){
        if(node != null) {
            node.print();
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node){
        if(node != null) {
            postOrder(node.left);
            postOrder(node.right);
            node.print();
        }
    }

    public Integer getHeight(){
        return getHeight(root);
    }

    private Integer getHeight(Node node) {
        //return Math.round(Math.log(size + 1)); formula
        if(node == null){
            return 0;
        }else{
            Integer lHeight = getHeight(node.left);
            Integer rHeight = getHeight(node.right);
            if(lHeight > rHeight) {
                return rHeight + 1;
            } else {
                return lHeight + 1;
            }
        }
    }

}
