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
            if (word.charAt(0) < node.word.charAt(0)){
                Node lNode = insert(node.l, word); //GOTO left child
                node.l = lNode; //Parent link
                lNode.p = node; 
            }
            else if (word.charAt(0) > node.word.charAt(0)){
                Node rNode = insert(node.r, word); //GOTO right child
                node.r = rNode; //Parent link
                rNode.p = node;
            }
            //updating the Node Height (h)
            heightOfNode++;
            if (node.h < heightOfNode) node.h = heightOfNode;
            //updating the Node BalanceFactor (Bf)
            update_balance_factor(node);
            //performing Rotations
            if (node.Bf == -2){ //right-?
                if (word.charAt(0) > node.r.word.charAt(0)){ //right
                    System.out.println("Left Rotation Performed!!");
                    if(node == root) root = node.r;
                    node = LeftRotation(node);
                    // height--;
                }
                else{ //left
                    System.out.println("Double: Right-Left Rotation performed!!: " + node.word);
                    node = Right_LeftRotation(node);
                }
            }
            else if (node.Bf == 2){ //left-?
                if (word.charAt(0) < node.l.word.charAt(0)){ //left
                    System.out.println("Right Rotation Performed!!");
                    if(node == root) root = node.l;
                    node = RightRotation(node);
                    // height--;
                }
                else{ //right
                    System.out.println("Double: Left-Right Rotation performed!!: " + node.word);
                    node = Left_RightRotation(node);
                }
            }
        }
        update_height(node);
        if(root == node) height = node.h;
        return node;
    }

    private Node LeftRotation(Node node) {
        Node pivot = node;
        Node rNode = node.r;
        rNode.p = pivot.p;
        pivot.p = rNode;
        pivot.r = rNode.l;
        rNode.l = pivot;
        //update the height of both nodes
        rNode.h = (rNode.r != null)? rNode.r.h + 1: 1;
        pivot.h -= 2;
        //update the balance factor of both nodes
        update_balance_factor(pivot);
        update_balance_factor(rNode);
        return rNode;
    }

    private Node RightRotation(Node node) {
        Node pivot = node;
        Node lNode = node.l;
        lNode.p = pivot.p;
        pivot.p = lNode;
        pivot.l = lNode.r;
        lNode.r = pivot;
        //update the height of both nodes
        lNode.h = (lNode.l != null)? lNode.l.h + 1: 1;
        pivot.h -= 2;
        //update the balance factor of both nodes
        update_balance_factor(pivot);
        update_balance_factor(lNode);
        return lNode;
    }

    private Node Left_RightRotation(Node node) {
        if(node == root) root = node.l.r;
        node = LeftRotation(node.l);
        node.p.l = node;
        node = RightRotation(node.p);
        return node;
    }

    private Node Right_LeftRotation(Node node) {
        if(node == root) root = node.r.l;
        node = RightRotation(node.r);
        node.p.r = node;
        node = LeftRotation(node.p);
        return node;
    }
    private void update_height(Node node){
        if(node.l == null
        && node.r == null)       node.h = 0; //leaf node
        else if (node.l == null) node.h = node.r.h + 1; //no left  subtree
        else if (node.r == null) node.h = node.l.h + 1; //no right subtree
        else node.h = (node.l.h > node.r.h )? node.l.h + 1: node.r.h + 1; //general case
    }
    
    private void update_balance_factor(Node node){
        if(node.l == null
        && node.r == null)       node.Bf = 0; //leaf node
        else if (node.l == null) node.Bf = (-1) - node.r.h; //no left  subtree
        else if (node.r == null) node.Bf = node.l.h - (-1); //no right subtree
        else                     node.Bf = node.l.h - node.r.h; //general case
    }

    public Node search(Node node, String word){
        if(node == null){
            System.out.println(word + ": NO");
        }
        else if (word.charAt(0) == node.word.charAt(0)){
            System.out.println(word + ": YES");
            return node;
        }
        else if (word.charAt(0) < node.word.charAt(0)){
            search(node.l, word);
        }
        else if (word.charAt(0) > node.word.charAt(0)){
            search(node.r, word);
        }
        return node;
    }
}
