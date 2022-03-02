
public class Node {
    
    public String word; //data
    public Node p; //parent
    public Node l; //left child
    public Node r; //right child

    //contruction
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