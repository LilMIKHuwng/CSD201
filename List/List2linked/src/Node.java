
public class Node {

    int info;
    Node prev, next;

    Node() {
    }

    Node(int x, Node p, Node q) {
        info = x;
        prev = p;
        next = q;
    }

    public static class MyList {

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
                head = tail = new Node(x, null, null);
            } else {
                Node q = new Node(x, tail, null);
                tail.next = q;
                tail = q;
            }
        }
        
        void addFirst(int value){
            head = new Node(value, null, head);
            head.next.prev = head;
            if (tail == null)
                tail = head;
        }

        void printList() {
            Node current = head;
            while (current != null) {
                System.out.print(current.info + " ");
                current = current.next;
            }
            System.out.println();
        }

        void sortList() {
            // Sắp xếp danh sách theo thuật toán nổi bọt (bubble sort)
            if (isEmpty() || head == tail) {
                return; // Không cần sắp xếp nếu danh sách rỗng hoặc chỉ có một phần tử
            }

            boolean swapped;
            Node current;
            Node last = null;

            do {
                swapped = false;
                current = head;

                while (current.next != last) {
                    if (current.info > current.next.info) {
                        // Hoán đổi giá trị của hai nút
                        int temp = current.info;
                        current.info = current.next.info;
                        current.next.info = temp;
                        swapped = true;
                    }
                    current = current.next;
                }
                last = current;
            } while (swapped);
        }

        void deleteAtBeginning() {
            if (isEmpty()) {
                System.out.println("Danh sách rỗng, không thể xóa.");
                return;
            }

            if (head == tail) {
                head = tail = null;
            } else {
                head = head.next;
                head.prev = null;
            }
        }

        void deleteAtEnd() {
            if (isEmpty()) {
                System.out.println("Danh sách rỗng, không thể xóa.");
                return;
            }

            if (head == tail) {
                head = tail = null;
            } else {
                tail = tail.prev;
                tail.next = null;
            }
        }

        void insertAtBeginning(int x) {
            Node newNode = new Node(x, null, head);
            if (isEmpty()) {
                head = tail = newNode;
            } else {
                head.prev = newNode;
                head = newNode;
            }
        }

        void insertAtEnd(int x) {
            Node newNode = new Node(x, tail, null);
            if (isEmpty()) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        void deleteValue(int x) {
            Node current = head;

            while (current != null) {
                if (current.info == x) {
                    if (current == head) {
                        deleteAtBeginning();
                    } else if (current == tail) {
                        deleteAtEnd();
                    } else {
                        current.prev.next = current.next;
                        current.next.prev = current.prev;
                    }
                    return;
                }
                current = current.next;
            }
            System.out.println("Không tìm thấy giá trị " + x + " trong danh sách.");
        }

        void deleteAllOccurrences(int x) {
            // Kiểm tra xem danh sách rỗng
            if (isEmpty()) {
                System.out.println("Danh sách rỗng, không thể xóa.");
                return;
            }

            Node current = head;

            while (current != null) {
                if (current.info == x) {
                    if (current == head) {
                        deleteAtBeginning();
                        current = head; // Dịch chuyển current đến nút đầu tiên sau khi xóa nút đầu
                    } else if (current == tail) {
                        deleteAtEnd();
                        current = null; // Kết thúc vòng lặp nếu xóa nút cuối
                    } else {
                        Node nextNode = current.next;
                        current.prev.next = nextNode;
                        nextNode.prev = current.prev;
                        current = nextNode;
                    }
                } else {
                    current = current.next;
                }
            }
        }

        void insertAtPosition(int x, int position) {
            if (position <= 0) {
                insertAtBeginning(x);
            } else {
                Node newNode = new Node(x, null, null);
                Node current = head;
                int currentPosition = 0;

                while (current != null) {
                    if (currentPosition == position) {
                        newNode.prev = current.prev;
                        newNode.next = current;
                        current.prev.next = newNode;
                        current.prev = newNode;
                        return;
                    }
                    current = current.next;
                    currentPosition++;
                }

                if (currentPosition == position) {
                    insertAtEnd(x);
                } else {
                    System.out.println("Vị trí không hợp lệ để chèn.");
                }
            }
        }

        void findTop3MaxValues() {
            if (isEmpty()) {
                System.out.println("Danh sách rỗng, không có giá trị lớn nhất.");
                return;
            }

            Node firstMax = head;
            Node secondMax = null;
            Node thirdMax = null;

            Node current = head;

            while (current != null) {
                if (current.info > firstMax.info) {
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

            System.out.println("Ba giá trị lớn nhất:");
            System.out.println("1. " + firstMax.info);
            if (secondMax != null) {
                System.out.println("2. " + secondMax.info);
            }
            if (thirdMax != null) {
                System.out.println("3. " + thirdMax.info);
            }
        }

        int getValueAtPosition(int position) {
            Node current = head;
            int currentPosition = 0;

            while (current != null) {
                if (currentPosition == position) {
                    return current.info;
                }
                current = current.next;
                currentPosition++;
            }

            System.out.println("Không tìm thấy giá trị tại vị trí " + position);
            return -1; // Trả về giá trị mặc định nếu không tìm thấy
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
        
        public static void main(String[] args) {
        MyList list = new MyList();
        list.add(2);
        list.add(6);
        list.add(1);
        list.add(4);
        list.add(7);

        list.printList();
        list.sortList();
        list.printList();
    }


    }

}
