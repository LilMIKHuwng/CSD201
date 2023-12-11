// =========================================================
// Do NOT modify this file 
// This queue is used for breadth-first search
// =========================================================

import java.util.*;
import javafx.scene.Node;
class Queue {
    private LinkedList<Integer> t;
    
    Queue() {
        t = new LinkedList<>();
    }
    
    boolean isEmpty() {
        return t.isEmpty();
    }
    
    void enqueue(int p) {
        t.addLast(p);
    }
   
    int dequeue() { 
        if (isEmpty()) 
            return -1; // Hoặc giá trị khác để biểu thị queue rỗng
     
        return t.removeFirst();
    }
    
    int front() { 
        if (isEmpty()) 
            return -1; // Hoặc giá trị khác để biểu thị queue rỗng
        
        return t.getFirst();
    }
}
