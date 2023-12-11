
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

    TreeNode root;

    public BinaryTree() {
        this.root = null;
    }

    public void breadthFirstTraversal() {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.print(current.value + " ");

            if (current.left != null) {
                queue.add(current.left);
            }

            if (current.right != null) {
                queue.add(current.right);
            }
        }

        System.out.println();
    }

    // Hàm chèn một giá trị vào cây
    public void insert(int value) {
        root = insertRec(root, value);
    }

    private TreeNode insertRec(TreeNode root, int value) {
        if (root == null) {
            root = new TreeNode(value);
            return root;
        }

        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else if (value > root.value) {
            root.right = insertRec(root.right, value);
        }

        return root;
    }

    // Duyệt cây theo thứ tự trung tố (in-order)
    public void inOrderTraversal() {
        inOrderRec(root);
        System.out.println();
        preOrder(root);
        System.out.println();
        postOrder(root);
    }

    public void inOrderRec(TreeNode root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.print(root.value + " ");
            inOrderRec(root.right);
        }
    }

    void preOrder(TreeNode p) {
        if (p == null) {
            return;
        }
        System.out.print(p.value + " ");
        preOrder(p.left);
        preOrder(p.right);
    }

    void postOrder(TreeNode p) {
        if (p == null) {
            return;
        }
        postOrder(p.left);
        postOrder(p.right);
        System.out.print(p.value + " ");
    }

    // Xóa một giá trị khỏi cây
    public void delete(int value) {
        root = deleteRec(root, value);
    }

    private TreeNode deleteRec(TreeNode root, int value) {
        if (root == null) {
            return root;
        }

        if (value < root.value) {
            root.left = deleteRec(root.left, value);
        } else if (value > root.value) {
            root.right = deleteRec(root.right, value);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.value = maxValue(root.left);

            root.left = deleteRec(root.right, root.value);
        }

        return root;
    }

    private int maxValue(TreeNode root) {
        int maxValue = root.value;
        while (root.right != null) {
            maxValue = root.right.value;
            root = root.right;
        }
        return maxValue;
    }

    public void balance() {
        int[] sortedValues = new int[getSize()];
        storeInOrderValues(root, sortedValues, 0);
        root = balanceFromArray(sortedValues, 0, sortedValues.length - 1);
    }

    private int getSize() {
        return getSize(root);
    }

    private int getSize(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + getSize(node.left) + getSize(node.right);
    }

    private void storeInOrderValues(TreeNode node, int[] sortedValues, int index) {
        if (node == null) {
            return;
        }
        storeInOrderValues(node.left, sortedValues, index);
        sortedValues[index++] = node.value;
        storeInOrderValues(node.right, sortedValues, index);
    }

    private TreeNode balanceFromArray(int[] sortedValues, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        TreeNode newNode = new TreeNode(sortedValues[mid]);

        newNode.left = balanceFromArray(sortedValues, start, mid - 1);
        newNode.right = balanceFromArray(sortedValues, mid + 1, end);

        return newNode;
    }

    public void balanceUsingRotations() {
        root = balanceUsingRotationsRec(root);
    }

    private TreeNode balanceUsingRotationsRec(TreeNode root) {
        if (root == null) {
            return null;
        }

        int balanceFactor = getBalanceFactor(root);

        // Trường hợp cần xoay trái
        if (balanceFactor > 1) {
            // Trường hợp xoay đôi trái
            if (getBalanceFactor(root.left) < 0) {
                root.left = Rotation.rotateWithRightChild(root.left);
            }
            return Rotation.rotateWithLeftChild(root);
        }

        // Trường hợp cần xoay phải
        if (balanceFactor < -1) {
            // Trường hợp xoay đôi phải
            if (getBalanceFactor(root.right) > 0) {
                root.right = Rotation.rotateWithLeftChild(root.right);
            }
            return Rotation.rotateWithRightChild(root);
        }

        return root;
    }

    private int getBalanceFactor(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public TreeNode deleteByMergingRec(TreeNode root, int value) {
        if (root == null) {
            return root;
        }

        if (value < root.value) {
            root.left = deleteByMergingRec(root.left, value);
        } else if (value > root.value) {
            root.right = deleteByMergingRec(root.right, value);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Tìm nút thay thế bằng giá trị lớn nhất bên trái
            TreeNode replacement = findMax(root.left);
            root.value = replacement.value;

            // Xóa nút thay thế từ cây con bên trái
            root.left = deleteByMergingRec(root.left, replacement.value);
        }

        return root;
    }

    private TreeNode findMax(TreeNode root) {
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    public void deleteByCopy(int value) {
        root = deleteByCopyRec(root, value);
    }

    private TreeNode deleteByCopyRec(TreeNode root, int value) {
        if (root == null) {
            return root;
        }

        if (value < root.value) {
            root.left = deleteByCopyRec(root.left, value);
        } else if (value > root.value) {
            root.right = deleteByCopyRec(root.right, value);
        } else {
            if (root.left == null && root.right == null) {
                return null; // Nút lá, không có con, chỉ cần xóa nút này
            } else if (root.left == null) {
                return root.right; // Nút có con bên phải, thay thế nút bằng con bên phải
            } else if (root.right == null) {
                return root.left; // Nút có con bên trái, thay thế nút bằng con bên trái
            }

            // Nút cần xóa có cả hai cây con, tìm nút thay thế bằng giá trị lớn nhất bên trái
            TreeNode replacement = findMax(root.left);
            root.value = replacement.value;

            // Xóa nút thay thế từ cây con bên trái
            root.left = deleteByCopyRec(root.left, replacement.value);
        }

        return root;
    }

}
