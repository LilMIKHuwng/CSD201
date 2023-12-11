
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        Graph graph = new Graph();
//
//        graph.addVertex("A");
//        graph.addVertex("B");
//        graph.addVertex("C");
//
//        graph.addEdge("A", "B");
//        graph.addEdge("B", "C");
//        graph.addEdge("C", "A");
//
//        List<String> vertices = graph.getVertices();
//        for (String vertex : vertices) {
//            List<String> neighbors = graph.getNeighbors(vertex);
//            System.out.print("Đỉnh " + vertex + " kết nối với: ");
//            for (String neighbor : neighbors) {
//                System.out.print(neighbor + " ");
//            }
//            System.out.println();
//        }
        int numVertices = 4;
        Graph graph = new Graph(numVertices);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 0);

        System.out.println("Ma trận kề của đồ thị:");
        graph.printGraph();

        System.out.println("Duyệt đồ thị theo chiều rộng (BFS) từ đỉnh 0:");
        graph.breadthFirst(0);

        System.out.println("Duyệt đồ thị theo chiều sâu (DFS) từ đỉnh 0:");
        graph.depthFirst(0);
        
        graph.dijkstra(1);
    }
}
