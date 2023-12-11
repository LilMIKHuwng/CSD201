
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
//    private Map<String, List<String>> adjList;
//
//    public Graph() {
//        this.adjList = new HashMap<>();
//    }
//
//    public void addVertex(String vertex) {
//        adjList.put(vertex, new ArrayList<>());
//    }
//
//    public void addEdge(String source, String destination) {
//        if (adjList.containsKey(source) && adjList.containsKey(destination)) {
//            adjList.get(source).add(destination);
//            adjList.get(destination).add(source); // Đối với đồ thị vô hướng
//        } else {
//            System.out.println("Đỉnh không tồn tại.");
//        }
//    }
//
//    public List<String> getVertices() {
//        return new ArrayList<>(adjList.keySet());
//    }
//
//    public List<String> getNeighbors(String vertex) {
//        return adjList.get(vertex);
//    }

    private int[][] adjacencyMatrix;
    private int numVertices;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        this.adjacencyMatrix = new int[numVertices][numVertices];
    }

    public void addEdge(int source, int destination) {
        if (source >= 0 && source < numVertices && destination >= 0 && destination < numVertices) {
            adjacencyMatrix[source][destination] = 1;
            adjacencyMatrix[destination][source] = 1; // Đối với đồ thị vô hướng
        } else {
            System.out.println("Đỉnh không tồn tại.");
        }
    }

    public void printGraph() {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    void breadthFirst(int k) {
        Queue queue = new Queue();
        boolean[] enqueued = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {
            enqueued[i] = false;
        }

        queue.enqueue(k);
        enqueued[k] = true;

        while (!queue.isEmpty()) {
            int h = queue.dequeue();
            visit(h);

            for (int i = 0; i < numVertices; i++) {
                if (!enqueued[i] && adjacencyMatrix[h][i] > 0) {
                    queue.enqueue(i);
                    enqueued[i] = true;
                }
            }
        }
        System.out.println();
    }

    void visit(int i) {
        System.out.print(" " + i);
    }

    void depthFirst(boolean visited[], int i) {
        visit(i);
        visited[i] = true;

        for (int j = 0; j < numVertices; j++) {
            if (adjacencyMatrix[i][j] > 0 && !visited[j]) {
                depthFirst(visited, j);
            }
        }
    }

    void depthFirst(int k) {
        boolean[] visited = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        depthFirst(visited, k);

        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                depthFirst(visited, i);
            }
        }
        System.out.println();
    }
    
    void dijkstra(int startVertex) {
        int[] distance = new int[numVertices];
        boolean[] shortestPathSet = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {
            distance[i] = Integer.MAX_VALUE;
            shortestPathSet[i] = false;
        }

        distance[startVertex] = 0;

        for (int count = 0; count < numVertices - 1; count++) {
            int u = minDistance(distance, shortestPathSet);
            shortestPathSet[u] = true;

            for (int v = 0; v < numVertices; v++) {
                if (!shortestPathSet[v] && adjacencyMatrix[u][v] > 0 && distance[u] != Integer.MAX_VALUE
                        && distance[u] + adjacencyMatrix[u][v] < distance[v]) {
                    distance[v] = distance[u] + adjacencyMatrix[u][v];
                }
            }
        }

        printDijkstraResults(startVertex, distance);
    }

    int minDistance(int[] distance, boolean[] shortestPathSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < numVertices; v++) {
            if (!shortestPathSet[v] && distance[v] <= min) {
                min = distance[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    void printDijkstraResults(int startVertex, int[] distance) {
        System.out.println("Khoảng cách từ đỉnh " + startVertex + " đến tất cả các đỉnh:");
        for (int i = 0; i < numVertices; i++) {
            System.out.println("Đỉnh " + i + ": " + distance[i]);
        }
    }

}
