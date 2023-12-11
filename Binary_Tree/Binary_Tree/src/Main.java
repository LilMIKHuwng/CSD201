
public class Main {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        // Thêm các giá trị vào cây
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int value : values) {
            binaryTree.insert(value);
        }

        System.out.println("Duyệt cây theo thứ tự trung tố:");
        binaryTree.inOrderRec(binaryTree.root);

        System.out.println("\nDuyệt cây theo thứ tự tiền trình:");
        binaryTree.preOrder(binaryTree.root);

        System.out.println("\nDuyệt cây theo thứ tự hậu trình:");
        binaryTree.postOrder(binaryTree.root);

        System.out.println("\nDuyệt cây theo chiều rộng:");
        binaryTree.breadthFirstTraversal();

        System.out.println("\nXóa giá trị 30 bằng cách xoá bằng copy:");
        binaryTree.deleteByCopy(30);
        binaryTree.inOrderRec(binaryTree.root);

        System.out.println("\nXóa giá trị 60 bằng cách xoá bằng merge:");
        binaryTree.deleteByMergingRec(binaryTree.root, 70);
        binaryTree.deleteByMergingRec(binaryTree.root, 60);
        binaryTree.deleteByMergingRec(binaryTree.root, 80);
        binaryTree.inOrderRec(binaryTree.root);

        System.out.println("\nCân bằng cây:");
        binaryTree.balanceUsingRotations();
        binaryTree.inOrderRec(binaryTree.root);
        System.out.println();
        System.out.println(binaryTree.root.value);
        System.out.println(binaryTree.root.left.value);
        System.out.println(binaryTree.root.right.value);
    }
    
}
