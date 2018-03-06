package trees;

import trees.lib.BinaryTree;
import trees.lib.Node;

import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderTraversal {

    /**
     * Level Order Traversal using Queue
     * @param binaryTree {BinaryTree}
     */
    public void process(BinaryTree binaryTree){
        if(!binaryTree.isEmpty()){ //checking if not empty
            Queue<Node> needToTraverse = new LinkedList<Node>(); //queue to store nodes that are going to be traversed
            needToTraverse.add(binaryTree.getRoot());
            while(!needToTraverse.isEmpty()){
                Node node = needToTraverse.poll();
                node.print();
                if(node.hasLeft()){
                    needToTraverse.add(node.left);
                }
                if(node.hasRight()){
                    needToTraverse.add(node.right);
                }
            }
        }
    }

    /**
     * Recursive approach
     * @param binaryTree {BinaryTree}
     */
    public void printLevelOrder(BinaryTree binaryTree){
        for(Integer i = 1; i <= binaryTree.getHeight(); i++){
            printLevel(binaryTree.getRoot(), i);
        }
    }

    /**
     *
     * @param node {Node}
     * @param level {Integer}
     */
    private void printLevel(Node node, Integer level){
        if(node == null){
            return;
        }
        if(level == 1){
            node.print();
        }else if(level > 1){
            printLevel(node.left, level - 1);
            printLevel(node.right, level - 1);
        }
    }

    public void printLevelByLevel(BinaryTree binaryTree){
        if(!binaryTree.isEmpty()){ //checking if not empty
            Queue<Node> queue = new LinkedList<Node>(); //queue to store nodes
            queue.add(binaryTree.getRoot());
            while(true){
                Integer nodeCount = queue.size();
                if(nodeCount == 0){
                    break;
                }
                while (nodeCount > 0){
                    Node node = queue.poll(); //dequeue each node
                    System.out.print(node.value + " ");
                    if(node.hasLeft()){
                        queue.add(node.left);
                    }
                    if(node.hasRight()){
                        queue.add(node.right);
                    }
                    nodeCount--;
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) throws Exception {

        BinaryTree binaryTree = new BinaryTree(new Node(16));
        binaryTree.insert(new Node(12));
        binaryTree.insert(new Node(18));
        binaryTree.insert(new Node(6));
        binaryTree.insert(new Node(13));
        binaryTree.insert(new Node(19));
        //System.out.println(binaryTree.size);

        LevelOrderTraversal levelOrderTraversal = new LevelOrderTraversal();

        //levelOrderTraversal.process(binaryTree); //method 1 using Queue

        //levelOrderTraversal.printLevelOrder(binaryTree); //recursive method

        //levelOrderTraversal.printLevelByLevel(binaryTree);


        //binaryTree.inOrder();
        //binaryTree.preOrder();
        binaryTree.postOrder();
        //System.out.println(binaryTree.getHeight());
    }

}
