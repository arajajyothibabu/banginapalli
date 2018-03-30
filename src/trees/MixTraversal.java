package trees;

import trees.lib.BinaryTree;
import trees.lib.Node;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 Given a Binary tree. Mix mix traversal with altering inorder and preorder for specified depth-d
 Step 1: for depth d print level order
 Step 2: for next depth d  print preorder
 Step 3: Repeat step 2, 3 till the complete tree is parsed
 */

public class MixTraversal {

    static void process(BinaryTree binaryTree, Integer depth){
        Integer depthIndex = 0;
        Boolean inLevelOrder = true;
        Queue<Node> needToTraverse = new LinkedList<Node>();
        needToTraverse.add(binaryTree.getRoot());
        while(!needToTraverse.isEmpty()){
            if(inLevelOrder){
                Node node = needToTraverse.poll();
                node.print();
                if(!depthIndex.equals(depth)) {
                    depthIndex++;
                    if (node.hasLeft()) {
                        needToTraverse.add(node.left);
                    }
                    if (node.hasRight()) {
                        needToTraverse.add(node.right);
                    }
                }else{
                    inLevelOrder = !inLevelOrder;
                    depthIndex = 0;
                }
            }else{
                Stack<Node> remainingNodes = new Stack<Node>();
                remainingNodes.add(needToTraverse.poll());
                while(remainingNodes.isEmpty()){
                    Node node = remainingNodes.pop();
                    if(!depthIndex.equals(depth)) {
                        depthIndex++;
                        if (node.hasRight()) {
                            remainingNodes.add(node.right);
                        }
                        if (node.hasLeft()) {
                            remainingNodes.add(node.left);
                        }
                    }else{
                        if (node.hasLeft()) {
                            needToTraverse.add(node.left);
                        }
                        if (node.hasRight()) {
                            needToTraverse.add(node.right);
                        }
                        inLevelOrder = false;
                        depthIndex = 0;
                    }
                }
                if(!remainingNodes.isEmpty()){

                }
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
    }

}
