public class BST {
    public Node root = null;
    public int height;
    public int heightOfInsert;

    public BST (){
        this.height = -1; // (-1) --> empty tree
        this.heightOfInsert = 0;
    }

    public Node insert(Node node, String word){
        if(node == null){
            System.out.println(heightOfInsert +"\n-------------------\n");
            if (height == -1)
                root = new Node(word);
            else
                node = new Node(word);
            //updating the Tree Height
            height = (height < heightOfInsert)? heightOfInsert: height;
            heightOfInsert = 0;
            return new Node(word);
        }
        else if (word.charAt(0) > node.word.charAt(0)){
            heightOfInsert++;
            Node lNode = insert(node.l, word);
            node.l = lNode;
            lNode.p = node; 
        }
        else if (word.charAt(0) < node.word.charAt(0)){
            heightOfInsert++;
            Node rNode = insert(node.r, word);
            node.r = rNode;
            rNode.p = node;
        }
        return node;
    }
}
