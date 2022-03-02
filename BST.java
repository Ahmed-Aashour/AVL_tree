public class BST {

    public Node root = null;
    public int height;

    private int heightOfInsert; //helping in --> getting the height of the BST
    private int heightOfNode;   //helping in --> getting the height of the nodes(subtree)

    //constructor
    public BST (){
        this.height = -1; // (-1) --> empty tree
        this.heightOfInsert = 0;
        this.heightOfNode = 0;
    }

    public Node insert(Node node, String word){
        if(node == null){
            Node newNode = new Node(word);
            if (height == -1)
                root = newNode;
            else
                node = newNode;
            //updating the Tree Height
            height = (height < heightOfInsert)? heightOfInsert: height;
            heightOfInsert = 0;
            heightOfNode = 0;
            return newNode;
        }
        else{
            heightOfInsert++;
            if (word.charAt(0) > node.word.charAt(0)){
                Node lNode = insert(node.l, word); //GOTO left child
                node.l = lNode; //Parent link
                lNode.p = node; 
            }
            else if (word.charAt(0) < node.word.charAt(0)){
                Node rNode = insert(node.r, word); //GOTO right child
                node.r = rNode; //Parent link
                rNode.p = node;
            }
            //updating the Node Height (h)
            heightOfNode++;
            if (node.h < heightOfNode) node.h = heightOfNode;
            //updating the Node BalanceHeight (Bh)
            if(node.l == null
            && node.r == null)       node.Bh = 0; //leaf node
            else if (node.l == null) node.Bh = (-1) - node.r.h; //no left subtree
            else if (node.r == null) node.Bh = node.l.h - (-1); //no right subtree
            else                     node.Bh = node.l.h - node.r.h; //general case
        }
        return node;
    }

    public void search(Node node, String word){
        if(node == null){
            System.out.println(word + ": NO");
        }
        else if (word.charAt(0) == node.word.charAt(0)){
            System.out.println(word + ": YES");
        }
        else if (word.charAt(0) > node.word.charAt(0)){
            search(node.l, word);
        }
        else if (word.charAt(0) < node.word.charAt(0)){
            search(node.r, word);
        }
    }
}
