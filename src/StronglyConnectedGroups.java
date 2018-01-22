import java.util.ArrayList;

class CyclicNode {

    public Integer value;
    public ArrayList<CyclicNode> children;

    public CyclicNode(Integer value, ArrayList<CyclicNode> children) {
        this.value = value;
        this.children = children;
    }

    public boolean contains(Integer value) {
        if(this.value.equals(value)){
            return true;
        }else{
            for(CyclicNode cyclicNode : children){
                return cyclicNode.value.equals(value);
            }
        }
        return false;
    }

    public CyclicNode add(CyclicNode cyclicNode){
        children.add(cyclicNode);
        return cyclicNode;
    }

    public void print(){
        System.out.println("Value: " + value);
        for(CyclicNode child: children){
            System.out.println("^");
            System.out.println("Child: " + child.value);
            child.print();
        }
    }

    public CyclicNode find(Integer value){
        if(this.value.equals(value)) return this;
        else {
            for(CyclicNode child : children){
                return child.find(value);
            }
        }
        return null;
    }

}

public class StronglyConnectedGroups {

    private Integer n, m;
    private CyclicNode root;

    public StronglyConnectedGroups(Integer n, Integer m) {
        this.n = n;
        this.m = m;
        this.constructGraph();
    }

    private void constructGraph(){
        CyclicNode start = root;
        for(Integer i = 1; i <= n; i++){
            CyclicNode cyclicNode = new CyclicNode(i, new ArrayList<CyclicNode>());
            if(i == 1){
                start = cyclicNode;
                root = cyclicNode;
            }else if(i == n){
                root = root.add(cyclicNode);
                root.add(start);
            }else{
                root = root.add(cyclicNode);
            }
        }
    }

    public void compute(){
        attachVertices(root, n + 1, m).print();
    }

    private CyclicNode attachVertices(CyclicNode cyclicNode, Integer index, Integer threshold){
        if(index <= threshold) {
            if (!cyclicNode.contains(index % n)) {
                return attachVertices(cyclicNode.add(cyclicNode.find(index % n)), index++, threshold);
            } else {
                for(CyclicNode child : cyclicNode.children){
                    return attachVertices(child.add(child.find(index % n)), index++, threshold);
                }
            }
            return cyclicNode;
        }else{
            return cyclicNode;
        }
    }

}
