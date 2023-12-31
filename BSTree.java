/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.io.*;
import java.util.*;

public class BSTree {

    Node root;

    BSTree() {
        root = null;
    }

    boolean isEmpty() {
        return (root == null);
    }

    void clear() {
        root = null;
    }

    void visit(Node p) {
        System.out.print("p.info: ");
        if (p != null) {
            System.out.println(p.info + " ");
        }
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void breadth(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        Queue q = new Queue();
        q.enqueue(p);
        Node r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            fvisit(r, f);
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

    void preOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        fvisit(p, f);
        preOrder(p.left, f);
        preOrder(p.right, f);
    }

    void inOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        inOrder(p.left, f);
        fvisit(p, f);
        inOrder(p.right, f);
    }

    void postOrder(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        postOrder(p.left, f);
        postOrder(p.right, f);
        fvisit(p, f);
    }

    void loadData(int k) { //do not edit this function
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            insert(a[i], b[i], c[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
    void insert(String xBrand, int xColor, int xWeight) {
        //You should insert here statements to complete this function
        if (xBrand.charAt(0) == 'A') {
            return;
        }
        Node curr = root;
        Node parent = null;
        Bike b = new Bike(xBrand, xColor, xWeight);
        Node newNode = new Node(b);
        if (isEmpty()) {
            root = newNode;
            return;
        } else {
            curr = root;
            parent = null;
            while (true) {
                parent = curr;
                if (curr.info.weight > b.weight) {
                    curr = curr.left;
                    if (curr == null) {
                        parent.left = newNode;
                        return;
                    }
                } else {
                    curr = curr.right;
                    if (curr == null) {
                        parent.right = newNode;
                        return;
                    }
                }
                if (curr.info.weight == b.weight) {
                    return;
                }
            }
        }
    }

//Do not edit this function. Your task is to complete insert function above only.
    void f1() throws Exception {
        clear();
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        inOrder(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    void postOrder2(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        postOrder2(p.left, f);
        postOrder2(p.right, f);
        if (p.info.color < 7) {
            fvisit(p, f);
        }
    }

    void f2() throws Exception {
        clear();
        loadData(5);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        postOrder(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
        postOrder2(root, f);

        //------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    boolean hasChild(Node p) {
        if (p.left != null && p.right != null) {
            return true;
        }
        return false;

    }

    Node breadthWithCondition(Node p, RandomAccessFile f) throws Exception {
        if (p == null) {
            return null;
        }
        Queue q = new Queue();
        q.enqueue(p);
        Node r;
        int count = 0;
        while (!q.isEmpty()) {
            r = q.dequeue();
            if (hasChild(r)) {
                count++;
                if (count == 2) {
                    return r;
                }
            }
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }

        return null;
    }

    Node search(Node p, int count) {
        if (p == null) {
            return null;
        }

        if (hasChild(p)) {
            count++;
        }

        if (count == 2) {
            return p;
        }

        Node leftResult = search(p.left, count);
        if (leftResult != null) {
            return leftResult;
        }

        Node rightResult = search(p.right, count);
        if (rightResult != null) {
            return rightResult;
        }

        return null;
    }

    void delete(Node x) {
        if (isEmpty()) {
            return;
        }
        Node p = root, f = null;
        while (p != null) {
            if (p.info == x.info) {
                break;
            }
            f = p;
            if (p.info.weight > x.info.weight) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (p == null) {
            return;
        }

        // No child
        if (p.left == null && p.right == null) {
            if (f == null) {
                root = null;
                return;
            }
            if (f.left == p) {
                f.left = null;
            } else {
                f.right = null;
            }
        }

        // 1 child
        if (p.left != null && p.right == null) {
            if (f == null) {
                root = p.left;
                return;
            }
            if (f.left == p) {
                f.left = p.left;
            } else {
                f.right = p.left;
            }
        } else if (p.left == null && p.right != null) {
            if (f == null) {
                root = p.right;
                return;
            }
            if (f.left == p) {
                f.left = p.right;
            } else {
                f.right = p.right;
            }
        }

        // 2 children
        if (p.left != null && p.right != null) {
            deleteByCopying(p);
        }
    }

    void deleteByCopying(Node p) {
        Node rightMost = p.left, f = null;
        while (rightMost.right != null) {
            f = rightMost;
            rightMost = rightMost.right;
        }
        p.info = rightMost.info;
        if (f == null) {
            p.left = rightMost.left;
        } else {
            f.right = rightMost.left;
        }
    }

    void f3() throws Exception {
        clear();
        loadData(9);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
        if (isEmpty()) {
            return;
        }
        Node p = breadthWithCondition(root, f);
        if (p != null) {
            delete(p.right);
        }

        //------------------------------------------------------------------------------------
        breadth(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

//=============================================================
    Node findMaxNode(Node p) {
        if (p.right == null) {
            return p;
        }
        return findMaxNode(p.right);
    }

    Node searchParent(Node a) {
        if (a == null) {
            return null;
        }
        Node p = root, f = null;
        while (p != null && p != a) {
            f = p;
            if (p.info.weight > a.info.weight) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return f;
    }

    Node rotateLeft(Node p) {
        if (p.right == null) {
            return p;
        }
        Node q = p.right;
        p.right = q.left;
        q.left = p;
        Node parent = searchParent(p);
        if (parent != null) {
            if (parent.left == p) {
                parent.left = q;
            } else {
                parent.right = q;
            }
        }

        return q;
    }

    public Node rotateRight(Node p) {
        if (p.left == null) {
            return p;
        }
        Node q = p.left;
        p.left = q.right;
        q.right = p;
        Node parent = searchParent(p);
        if (parent != null) {
            if (parent.left == p) {
                parent.left = q;
            } else {
                parent.right = q;
            }
        }

        return q;
    }

    Node rotateWithLeftChild(Node k2) {
        Node k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }

    Node rotateWithRightChild(Node k1) {
        Node k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        return k2;
    }

    Node doubleRotateWithLeftChild(Node k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    Node doubleRotateWithRightChild(Node k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    void f4() throws Exception {
        clear();
        loadData(13);;
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(root, f);
        f.writeBytes("\r\n");
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
        Node p = findMaxNode(root);
        rotateLeft(searchParent(p));

        //------------------------------------------------------------------------------------
        breadth(root, f);
        f.writeBytes("\r\n");
        f.close();
    }

}
