package List;

public class Node {

    int info;
    Node next;

    Node() {
    }

    Node(int x, Node p) {
        info = x;
        next = p;
    }
}

class MyList {

    Node head, tail;

    MyList() {
        head = tail = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = tail = null;
    }

    void add(int x) {
        if (isEmpty()) {
            head = tail = new Node(x, null);
        } else {
            Node q = new Node(x, null);
            tail.next = q;
            tail = q;
        }
    }

    void traverse() {
        Node p = head;
        while (p != null) {
            System.out.print("  " + p.info);
            p = p.next;
        }
        System.out.println();
    }

    Node search(int x) {

        Node p = head;
        while (p != null) {
            if (p.info == x) {
                return p; // Trả về nút chứa giá trị cần tìm
            }
            p = p.next;
        }
        return null;
    }

    void dele(int x) {
        // Kiểm tra xem danh sách rỗng
        if (isEmpty()) {
            System.out.println("Danh sách rỗng, không thể xóa.");
            return;
        }

        // Trường hợp đặc biệt: Nếu nút cần xóa nằm ở đầu danh sách
        if (head.info == x) {
            head = head.next;
            if (head == null) {
                // Nếu danh sách chỉ có một phần tử, cập nhật cả tail
                tail = null;
            }
            return;
        }

        // Tìm nút trước nút chứa giá trị cần xóa
        Node prev = head;
        while (prev.next != null && prev.next.info != x) {
            prev = prev.next;
        }

        // Nếu prev.next là null, có nghĩa là không tìm thấy giá trị x
        if (prev.next == null) {
            System.out.println("Không tìm thấy giá trị " + x + " trong danh sách.");
            return;
        }

        // Xóa nút chứa giá trị x khỏi danh sách
        prev.next = prev.next.next;

        // Nếu nút bị xóa là tail, cập nhật tail
        if (prev.next == null) {
            tail = prev;
        }
    }

    void printTop3Nodes() {
        Node firstMax = null;
        Node secondMax = null;
        Node thirdMax = null;

        Node current = head;

        // Nếu danh sách rỗng hoặc chỉ có một nút, không thực hiện gì
        if (isEmpty() || head.next == null || head.next.next == null) {
            System.out.println("Không có đủ nút để in ra.");
            return;
        }

        while (current != null) {
            if (firstMax == null || current.info > firstMax.info) {
                // 1 5 9 third = second = null ; second = null ; first = 1
                // third = null ; second = 1 ;first = 5;
                thirdMax = secondMax;
                secondMax = firstMax;
                firstMax = current;
            } else if (secondMax == null || current.info > secondMax.info) {
                thirdMax = secondMax;
                secondMax = current;
            } else if (thirdMax == null || current.info > thirdMax.info) {
                thirdMax = current;
            }

            current = current.next;
        }

        System.out.println("Ba nút có giá trị lớn nhất:");
        System.out.println("1. " + firstMax.info);
        System.out.println("2. " + secondMax.info);
        System.out.println("3. " + thirdMax.info);
    }

    void printNodesWithValue(int x) {
        Node current = head;
        int position = 0;
        boolean found = false;

        while (current != null) {
            if (current.info == x) {
                System.out.println("Node có giá trị " + x + " ở vị trí " + position);
                found = true;
            }
            current = current.next;
            position++;
        }

        if (!found) {
            System.out.println("Không tìm thấy nút nào có giá trị " + x);
        }
    }

    void deleteNodesWithValue(int x) {
        // Kiểm tra xem danh sách rỗng
        if (isEmpty()) {
            System.out.println("Danh sách rỗng, không thể xóa.");
            return;
        }

        // Xóa các nút có giá trị x từ đầu danh sách (nếu có)
        while (head != null && head.info == x) {
            head = head.next;
        }

        Node current = head;
        Node prev = null;

        while (current != null) {
            if (current.info == x) {
                // Xóa nút có giá trị x
                if (prev != null) {
                    prev.next = current.next;
                }
                // Nếu nút bị xóa là tail, cập nhật tail
                if (current == tail) {
                    tail = prev;
                }
                current = current.next;
            } else {
                prev = current;
                current = current.next;
            }
        }
    }

    void insertBefore(int x, int newValue) {
        // Kiểm tra xem danh sách rỗng
        if (isEmpty()) {
            System.out.println("Danh sách rỗng, không thể chèn.");
            return;
        }

        // Trường hợp đặc biệt: Nếu giá trị cần chèn vào trước là giá trị của nút đầu tiên
        if (head.info == x) {
            head = new Node(newValue, head);
            return;
        }

        Node current = head;
        Node prev = null;

        while (current != null) {
            if (current.info == x) {
                // Chèn giá trị mới trước giá trị x
                Node newNode = new Node(newValue, current);
                prev.next = newNode;
                return;
            }

            prev = current;
            current = current.next;
        }

        System.out.println("Không tìm thấy giá trị " + x + " trong danh sách.");
    }

    void insertAfter(int x, int newValue) {
        // Kiểm tra xem danh sách rỗng
        if (isEmpty()) {
            System.out.println("Danh sách rỗng, không thể chèn.");
            return;
        }

        Node current = head;

        while (current != null) {
            if (current.info == x) {
                // Tạo nút mới chứa giá trị mới và liên kết nó với nút sau nút có giá trị x
                Node newNode = new Node(newValue, current.next);
                current.next = newNode;
                // Nếu nút cuối danh sách là nút có giá trị x, cập nhật tail
                if (current == tail) {
                    tail = newNode;
                }
                return;
            }

            current = current.next;
        }

        System.out.println("Không tìm thấy giá trị " + x + " trong danh sách.");
    }

    void selectionSort() {
        Node current = head;
        while (current != null) {
            Node min = current;
            Node temp = current.next;

            while (temp != null) {
                if (temp.info < min.info) {
                    min = temp;
                }
                temp = temp.next;
            }

            // Swap giá trị của current và min
            int tempValue = current.info;
            current.info = min.info;
            min.info = tempValue;

            current = current.next;
        }
    }

    void insertAtBeginning(int newValue) {
        // Tạo nút mới với giá trị mới và nối nút mới với nút hiện tại của đầu danh sách
        Node newNode = new Node(newValue, head);
        head = newNode;

        // Nếu danh sách trống, cập nhật tail
        if (tail == null) {
            tail = newNode;
        }
    }

    void insertAtEnd(int newValue) {
        // Tạo nút mới với giá trị mới
        Node newNode = new Node(newValue, null);

        // Nếu danh sách trống, cập nhật đầu và cuối danh sách
        if (isEmpty()) {
            head = tail = newNode;
            return;
        }

        // Nối nút mới với tail và cập nhật tail thành nút mới
        tail.next = newNode;
        tail = newNode;
    }

    void insertAt5orEnd(int newValue) {
        // Tạo nút mới với giá trị mới
        Node newNode = new Node(newValue, null);

        // Nếu danh sách rỗng, hoặc danh sách có ít hơn 5 phần tử, chèn vào cuối danh sách
        if (isEmpty() || countNode() <= 5) {
            insertAtEnd(newValue);
            return;
        }

        // Nếu danh sách có ít nhất 5 phần tử, chèn vào vị trí thứ 5
        Node current = head;
        int position = 1;

        while (current != null && position < 4) {
            current = current.next;
            position++;
        }

//        // Nếu current là null hoặc position là 4, chèn vào cuối danh sách
//        if (current == null || position == 4) {
//            insertAtEnd(newValue);
//            return;
//        }
        // Chèn vào vị trí thứ 5
        newNode.next = current.next;
        current.next = newNode;
    }

    void ascendingNode() {
        int end = countNode();
        for (int i = 0; i < end; i++) {
            for (int j = i; j < end - 1; j++) {
                if (getNode(i).info < getNode(j).info) {
                    swap(getNode(i), getNode(j));
                }
            }
        }
    }

    int countNode() {
        Node p = head;
        int count = 1;
        // 1 2 3
        while (p != null) {
            count++;
            p = p.next;
        }
        return count;
    }

    void swap(Node a, Node b) {
        int tmp = a.info;
        a.info = b.info;
        b.info = tmp;
    }

    int countNodes() {
        int count = 0;
        Node current = head;

        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }

    Node getNode(int k) {
        int count = 0;
        Node p = head;
        while (p != null) {
            if (count == k) {
                return p;
            }
            p = p.next;
            count++;
        }
        return null;
    }

    public void deleteFirst() {
        if (head == tail) {
            head = tail = null;
        } else if (head != null) {
            head = head.next;
        }
    }

    void deleteAtEnd() {
        // Kiểm tra xem danh sách rỗng
        if (isEmpty()) {
            System.out.println("Danh sách rỗng, không thể xóa.");
            return;
        }

        // Trường hợp đặc biệt: Nếu danh sách chỉ có một phần tử, xóa nút và cập nhật head và tail
        if (head == tail) {
            head = tail = null;
            return;
        }

        // Tìm nút trước nút cuối
        Node prev = head;
        while (prev.next != tail) {
            prev = prev.next;
        }

        // Xóa nút cuối và cập nhật tail
        prev.next = null;
        tail = prev;
    }

    public static void main(String[] args) {
        MyList list = new MyList();
        list.add(2);
        list.add(6);
        list.add(1);
        list.add(4);
        list.add(7);
//        list.add(9);
        list.traverse();
        list.insertAt5orEnd(8);
        System.out.println(list.countNode());
//        list.selectionSort();
        list.traverse();
//        list.selectionSort();
//        list.traverse();
//        list.deleteFirst();
//        list.deleteNodesWithValue(3);
//        list.traverse();
//        list.deleteNodesWithValue(1);
//        list.traverse();
//        list.printTop3Nodes();
//        list.dele(0);
//        list.traverse();
//        System.out.println(list.search(10));
//        list.printTop3Nodes();
//
//        list.printNodesWithValue(9);
//        list.deleteNodesWithValue(9);
//        list.traverse();
//        list.insertBefore(0, 8);
//        list.traverse();
    }
}
