import java.util.Scanner;

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class BST {
    TreeNode root;

    BST() {
        root = null;
    }

    void insert(int data) {
        root = insertRec(root, data);
    }

    TreeNode insertRec(TreeNode root, int data) {
        if (root == null) {
            root = new TreeNode(data);
            return root;
        }

        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }

        return root;
    }

    void preorder(TreeNode root) {
        if (root != null) {
            System.out.print(root.data + "->");
            preorder(root.left);
            preorder(root.right);
        }
    }

    void findSiblingAndPrint(int target) {
        TreeNode sibling = findSibling(root, target);
        if (sibling != null) {
            preorder(sibling);
        } else {
            System.out.print("NULL");
        }
    }

    TreeNode findSibling(TreeNode root, int target) {
        if (root == null || root.left == null)
            return null;

        if (root.left.data == target)
            return root.right;

        if (root.right == null)
            return null;

        if (root.right.data == target)
            return root.left;

        if (target < root.data)
            return findSibling(root.left, target);

        return findSibling(root.right, target);
    }
}

public class bipartite_graph {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int W = scanner.nextInt();
        BST bst = new BST();

        for (int i = 0; i < N; i++) {
            int fruitWeight = scanner.nextInt();
            bst.insert(fruitWeight);
        }

        bst.findSiblingAndPrint(W);
    }
}
