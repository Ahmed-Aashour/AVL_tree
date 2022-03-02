

/**
 * AVL_tree
 */
public class AVL_tree {

    public static void main(String[] args) {
        BST tree = new BST();
        tree.insert(tree.root, "g");
        tree.insert(tree.root, "e");
        tree.insert(tree.root, "b");
        tree.insert(tree.root, "h");
        System.out.println(" " + tree.root.word);
        System.out.println("/ \\");
        System.out.println(tree.root.l.word + " " + tree.root.r.word);
        System.out.println("Height = " + tree.height);
        System.out.println("Balance hight of " + tree.root.word + " = " + tree.root.Bh);
        
        tree.search(tree.root, "c");
    }
}