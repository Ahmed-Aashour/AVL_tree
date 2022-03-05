
public class Node {
    
    public String word; //data
    public Node p;      //parent
    public Node l;      //left child
    public Node r;      //right child
    public int h = 0;  //height of the subtree (if root --> height of the tree)
    public int Bf = 0;  //balance factor of the node (Height<leftSubtree> - Height<rightSubtree>)

    //contructors
    public Node(){
        this.word = null;
        this.p = null;
        this.l = null;
        this.r = null;
    }
    public Node (String word) {
        this.word = word;
        this.p = null;
        this.l = null;
        this.r = null;
    }
}