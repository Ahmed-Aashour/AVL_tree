

/**
 * AVL_tree
 */
public class AVL_tree {

    public static void main(String[] args) {
        
        BST tree = new BST();
        Application app = new Application(tree);
        app.startApplication();
        tree.insert(tree.root, "car");
        tree.insert(tree.root, "tack");
        tree.insert(tree.root, "tacj");
        tree.insert(tree.root, "fort");
        tree.insert(tree.root, "big");
        tree.insert(tree.root, "bat");
        tree.insert(tree.root, "lama");
        // tree.insert(tree.root, "s");
        // tree.insert(tree.root, "b");
        // tree.insert(tree.root, "e");
        // tree.delete("big");
        tree.preOrder(tree.root);
        // System.out.println("  " + tree.root.word);
        // System.out.println(" / \\");
        // System.out.println(" " + tree.root.l.word + "  " + tree.root.r.word);
        // System.out.println("   / \\");
        // System.out.println("   " + tree.root.r.l.word + "  " + tree.root.r.r.word);
        // System.out.println("Height = " + tree.height);
        // System.out.println("Height of " + tree.root.word + " = " + tree.root.h);
        // System.out.println("Balance Factor of " + tree.root.r.word + " = " + tree.root.r.Bf);
        
        // Node wanted = tree.search(tree.root, "c");
        // System.out.println(wanted.word);
    }
}