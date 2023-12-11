
package stack_queue;

import java.util.ArrayList;
import java.util.Collections;

class Patient {
    private String name;
    private int age;
    private String healthStatus;
    private String relationshipWithDoctor;
    private String membershipLevel;

    public Patient(String name, int age, String healthStatus, String relationshipWithDoctor, String membershipLevel) {
        this.name = name;
        this.age = age;
        this.healthStatus = healthStatus;
        this.relationshipWithDoctor = relationshipWithDoctor;
        this.membershipLevel = membershipLevel;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public String getRelationshipWithDoctor() {
        return relationshipWithDoctor;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }
}

class Node implements Comparable<Node>{
    private Patient patient;
    private int priority;

    public Node(Patient patient) {
        this.patient = patient;
        this.priority = calculatePriority();
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    public int getPriority() {
        return priority;
    }

    private int calculatePriority() {
        int priority = 0;

        if ("Cấp cứu".equals(patient.getHealthStatus())) {
            priority += 8;
        } 
        if ("Người VIP".equals(patient.getMembershipLevel())){
            priority += 2;
        }    
        if (patient.getAge() > 60 || patient.getAge() < 6) {
            priority += 1;
        }
        if ("Gia đình bác sĩ".equals(patient.getRelationshipWithDoctor())) {
            priority += 4;
        }

        return priority;
    }

    @Override
    public int compareTo(Node t) {
        return this.priority < t.priority ? 1 : this.priority == t.priority ? 0 : -1;
    }
    
    
}

class PriorityQueue {
    private ArrayList<Node> queue= new ArrayList<>();

//    public PriorityQueue() {
//        queue = new ArrayList<>();
//    }
//
    public void enqueue(Patient patient) {
        Node node = new Node(patient);
        queue.add(node);
        Collections.sort(queue);
    }
//
//    public Node dequeue() {
//        if (!isEmpty()) {
//            return queue.remove(0);
//        } else {
//            return null;
//        }
//    }
//
//    public boolean isEmpty() {
//        return queue.isEmpty();
//    }
    public void print(){
        for (Node node : queue) {
            System.out.println(node.getPatient().getName() + " - Điểm ưu tiên: " + node.getPriority());
        }
    }



    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue();
        
        Patient patient2 = new Patient("Lil-Huwng", 65, "Thường", "Gia đình bác sĩ", "Người VIP");
        Patient patient1 = new Patient("Na Nii", 45, "Cấp cứu", "Không phải Gia đình bác sĩ", "Người bình thường");
        Patient patient3 = new Patient("Lil Wuyn", 30, "Thường", "Gia đình bác sĩ", "Người bình thường");

        queue.enqueue(patient1);
        queue.enqueue(patient2);
        queue.enqueue(patient3);
        
        queue.print();
        
//        Node node2 = new Node(patient2);
//        Node node1 = new Node(patient1);
//        Node node3 = new Node(patient3);
        
        
//        while (!queue.isEmpty()) {
//            Node node = queue.dequeue();
//            System.out.println(node.getPatient().getName() + " - Điểm ưu tiên: " + node.getPriority());
//        }
    }
}    
