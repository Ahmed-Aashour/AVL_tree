public class BST {
    
    public Node root = null;
    public int height;
    public int heightOfInsert;

    //constructor
    public BST (){
        this.height = -1; // (-1) --> empty tree
        this.heightOfInsert = 0;
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
            return newNode;
        }
        else if (word.charAt(0) > node.word.charAt(0)){
            heightOfInsert++;
            Node lNode = insert(node.l, word); //GOTO left child
            node.l = lNode;
            lNode.p = node; 
        }
        else if (word.charAt(0) < node.word.charAt(0)){
            heightOfInsert++;
            Node rNode = insert(node.r, word); //GOTO right child
            node.r = rNode;
            rNode.p = node;
        }
        return node;
    }
}
