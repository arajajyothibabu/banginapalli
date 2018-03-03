package trees.lib;

import java.util.NoSuchElementException;

/**
 *
 */
public class BinaryTree extends Exception {

    private Node root;

    public Integer size = 0;

    public BinaryTree() { }

    /**
     *
     * @param root
     */
    public BinaryTree(Node root) {
        this.root = root;
        this.size++;
    }

    /**
     *
     * @return root
     */
    public Node getRoot() {
        return root;
    }

    /**
     *
     * @param root
     */
    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     *
     * @return {Boolean}
     */
    public Boolean isEmpty(){
        return root == null;
    }

    /**
     *
     * @param value
     * @return
     */
    public Boolean contains(Integer value){
        return contains(root, value);
    }

    /**
     *
     * @param node
     * @param value
     * @return
     */
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

    /**
     *
     * @param node
     * @param value
     * @return
     */
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

    /**
     *
     * @param node
     */
    public void insert(Node node){
        root = insert(root, node);
    }

    /**
     *
     * @param value
     */
    public void delete(Integer value){
        root = delete(root, value);
    }

    /**
     *
     * @param node
     * @return
     */
    private Integer findMin(Node node){
        return node.left == null ? node.value : findMin(node.left);
    }

    /**
     *
     * @param node
     * @return
     */
    private Integer findMax(Node node){
        return node.right == null ? node.value : findMin(node.right);
    }

    /**
     *
     * @return
     */
    public Integer min() {
        if(isEmpty()){
            throw new NoSuchElementException("Tree is Empty..!");
        }else{
            return findMin(root);
        }
    }

    /**
     *
     * @return
     */
    public Integer max(){
        if(isEmpty()){
            throw new NoSuchElementException("Tree is Empty..!");
        }else{
            return findMax(root);
        }
    }

    /**
     *
     * @param node
     * @param value
     * @return
     */
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

    /**
     *
     * @param node
     * @param newNode
     * @return
     */
    private Node insert(Node node, Node newNode){
        if(node == null){
            node = newNode;
            this.size++; //incrementing tree size
        }else if(node.value > newNode.value){
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

    /**
     * InOrder [LCR]
     */
    public void inOrder(){
        inOrder(root);
    }

    /**
     *
     * @param node
     */
    private void inOrder(Node node){
        if(node != null) {
            inOrder(node.left);
            node.print();
            inOrder(node.right);
        }
    }

    /**
     * PreOder [CLR]
     */
    public void preOrder(){
        preOrder(root);
    }

    /**
     *
     * @param node
     */
    private void preOrder(Node node){
        if(node != null) {
            node.print();
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * PostOrder [LRC]
     */
    public void postOrder(){
        postOrder(root);
    }

    /**
     *
     * @param node
     */
    private void postOrder(Node node){
        if(node != null) {
            postOrder(node.left);
            postOrder(node.right);
            node.print();
        }
    }

    /**
     *
     * @return
     */
    public Integer getHeight(){
        return getHeight(root);
    }

    /**
     *
     * @param node
     * @return
     */
    private Integer getHeight(Node node) {
        //return Math.round(Math.log(size + 1)); formula
        if(node == null){
            return 0;
        }else{
            Integer lHeight = getHeight(node.left);
            Integer rHeight = getHeight(node.right);
            if(lHeight > rHeight) {
                return lHeight + 1;
            } else {
                return rHeight + 1;
            }
        }
    }

}
