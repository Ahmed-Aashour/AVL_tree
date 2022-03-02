

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
        System.out.println(tree.root.word +", "+ tree.root.r.word);

        
    }
}