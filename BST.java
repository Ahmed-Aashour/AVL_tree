public class BST {

    public Node root = null;
    public int height;
    public int size;

    private int heightOfInsert; //helping in --> getting the height of the BST

    //constructor
    public BST (){
        this.height = -1; // (-1) --> empty tree
        size = 0;
        this.heightOfInsert = 0;
    }

    public Node insert(Node node, String word){
        if(node == null){
            Node newNode = new Node(word);
            if (height == -1) root = newNode;
            node = newNode;
            //updating the Tree Height
            height = (height < heightOfInsert)? heightOfInsert: height;
            heightOfInsert = 0;
            size++; //increment the words number
        }
        else if (word.compareTo(node.word) == 0){
            System.out.println(word + " :Already exist!!");}
        else{
            heightOfInsert++;
            if (word.compareTo(node.word) < 0){
                Node lNode = insert(node.l, word); //GOTO left child
                node.l = lNode;
                lNode.p = node; //Parent link 
            }
            else if (word.compareTo(node.word) > 0){
                Node rNode = insert(node.r, word); //GOTO right child
                node.r = rNode;
                rNode.p = node; //Parent link
            }
            update_height(node); //updating the Node Height (h)
            update_balance_factor(node); //updating the Node BalanceFactor (Bf)
            //performing Rotations
            if (node.Bf == -2){ //right-?
                if (word.compareTo(node.r.word) > 0){ //right
                    System.out.println("Left Rotation Performed!!: " + node.word);
                    if(node == root) root = node.r;
                    node = LeftRotation(node);
                }
                else{ //left
                    System.out.println("Double: Right-Left Rotation performed!!: " + node.word);
                    node = Right_LeftRotation(node);
                }
            }
            else if (node.Bf == 2){ //left-?
                if (word.compareTo(node.l.word) < 0){ //left
                    System.out.println("Right Rotation Performed!!: " + node.word);
                    if(node == root) root = node.l;
                    node = RightRotation(node);
                }
                else{ //right
                    System.out.println("Double: Left-Right Rotation performed!!: " + node.word);
                    node = Left_RightRotation(node);
                }
            }
        }
        return node;
    }

    private Node LeftRotation(Node node) {
        Node pivot = node;
        Node rNode = node.r;
        if(rNode.l != null) rNode.l.p = pivot;
        rNode.p = pivot.p;
        if(rNode.p != null && rNode.p.l == pivot)
        {
            rNode.p.l = rNode;
        }
        if(rNode.p != null && rNode.p.r == pivot)
        {
            rNode.p.r = rNode;
        }
        pivot.p = rNode;
        pivot.r = rNode.l;
        rNode.l = pivot;
        update_height(pivot); //update the height of both nodes
        update_height(rNode);
        update_balance_factor(pivot); //update the balance factor of both nodes
        update_balance_factor(rNode);
        return rNode;
    }

    private Node RightRotation(Node node) {
        Node pivot = node;
        Node lNode = node.l;
        if(lNode.r != null) lNode.r.p = pivot;
        lNode.p = pivot.p;
        if(lNode.p != null && lNode.p.l == pivot)
        {
            lNode.p.l = lNode;
        }
        if(lNode.p != null && lNode.p.r == pivot)
        {
            lNode.p.r = lNode;
        }
        pivot.p = lNode;
        pivot.l = lNode.r;
        lNode.r = pivot;
        update_height(pivot); //update the height of both nodes
        update_height(lNode);
        update_balance_factor(pivot); //update the balance factor of both nodes
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

    //a method to update height and balance of nodes after deletion operation
    private void update_balance_and_height(Node node){
        Node parent = node.p;
        if(parent == null)
        {
            this.update_balance_factor(node);
            this.balanceDeletion(node);
            this.update_height(node);
            return;
        }
        this.update_balance_factor(node);
        this.balanceDeletion(node);
        this.update_height(node);
        this.update_balance_and_height(parent);
    }

    public Node search(Node node, String word){
        if(node == null){
            System.out.println(word + ": NO");
            return null;
        }
        else if (word.compareTo(node.word) == 0){
            System.out.println(word + ": YES");
            return node;
        }
        else if (word.compareTo(node.word) < 0){
            node = search(node.l, word);
        }
        else if (word.compareTo(node.word) > 0){
            node = search(node.r, word);
        }
        return node;
    }
    public void delete(String word){
        Node node = this.search(this.root, word);
        //if the word to be deleted is null return
        if(node == null){
            System.out.println("word not found");
            return;
        }
        else
        {
            //if the node has no children
            Node parent = node.p;
            if(node.l == null && node.r == null)
            {
                if(node == this.root)
                {
                    this.root = null;
                }
                else
                {
                    if(parent.l == node){parent.l = null;}
                    else{parent.r = null;}
                    node.p = null;
                    parent.h--;
                    this.update_balance_and_height(parent);
                }
                this.size--;
            }
            //if the node have two children the successor wil be the minimum value in the right subtree
            else if(node.l != null && node.r != null)
            {
                Node successor = this.findMin(node.r);
                String temp = successor.word;
                this.delete(successor.word);
                node.word = temp;
            }
            //if the node have only one child
            else
            {
                Node child = node.l == null? node.r : node.l;
                if(node == this.root)
                {
                    this.root = child;
                    this.root.p = null;
                    this.root.h--;
                    return;
                }
                node.p = parent;
                child.p = parent;
                if(parent.l == node){
                    parent.l = child;   
                }
                else{parent.r = child;}
                node.p = null;
                parent.h--;
                this.update_balance_and_height(parent);
                this.size--;
            }
        }
    }

    private void balanceDeletion(Node node)
    {
        if(node.Bf > 1)
        {
            System.out.print("deletion balance needed for ");
            System.out.println(node.word + "  "+ node.Bf);
            if(node.l != null && node.l.Bf >= 0)
            {
                //left left case do right rotation for this node
                this.RightRotation(node);
                System.out.println("left left");

            }
            else if(node.l != null && node.l.Bf < 0)
            {
                //left right rotation 
                //do a left rotation at left child of current node followed
                //by a right rotation at the current node itself.
                this.Left_RightRotation(node);
                System.out.println("left right");
            }
        }

        if(node.Bf < -1)
        {
            System.out.print("deletion balance needed for ");
            System.out.println(node.word + "  "+ node.Bf);
            if(node.r != null && node.r.Bf <= 0)
            {
                //left left case do right rotation for this node
                this.LeftRotation(node);
                System.out.println("right right");

            }
            else if(node.r != null && node.r.Bf > 0)
            {
                //left right rotation 
                //do a left rotation at left child of current node followed
                //by a right rotation at the current node itself.
                this.Right_LeftRotation(node);
                System.out.println("right left");
            }
        }
    }

    private Node findMin(Node node)
    {
        if(node.l != null){
            node = node.l;
        }
        return node;
    }

    // method used for debuggig purpose only
    public void preOrder(Node node)
    {
        if (node != null)
        {
            System.out.println(node.word + " balance factor = " + node.Bf + ", height = " + node.h);
            // if(node.p != null)
            // {
            //     System.out.println("parent is "+ node.p.word + " ");
            // }
            preOrder(node.l);
            preOrder(node.r);
        }
    }

}
