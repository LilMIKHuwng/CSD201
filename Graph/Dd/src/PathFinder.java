public class PathFinder {
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 0, 0, 1, 1, 0},
            {1, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 0, 1},
        };

        int[][] path = findPath(matrix);

        if (path != null) {
            System.out.println("Path found:");
            printMatrix(path);
        } else {
            System.out.println("No path found.");
        }
    }

    public static int[][] findPath(int[][] matrix) {
        int numRows = matrix.length;
        if (numRows == 0) {
            return null; // Ma trận rỗng
        }

        int numCols = matrix[0].length;
        if (numCols == 0) {
            return null; // Ma trận rỗng
        }

        boolean[][] visited = new boolean[numRows][numCols];
        int[][] path = new int[numRows][numCols];

        if (dfs(matrix, visited, path, 0, 0, numRows - 1, numCols - 1)) {
            return path;
        } else {
            return null; // Không tìm thấy đường đi
        }
    }

    public static boolean dfs(int[][] matrix, boolean[][] visited, int[][] path, int row, int col, int numRows, int numCols) {
        if (row < 0 || col < 0 || row >= numRows || col >= numCols || matrix[row][col] == 0 || visited[row][col]) {
            return false;
        }

        visited[row][col] = true;
        path[row][col] = 1;

        if (row == numRows - 1 && col == numCols - 1) {
            return true; // Tìm thấy đích
        }

        if (dfs(matrix, visited, path, row + 1, col, numRows, numCols) ||
            dfs(matrix, visited, path, row, col + 1, numRows, numCols) ||
            dfs(matrix, visited, path, row - 1, col, numRows, numCols) ||
            dfs(matrix, visited, path, row, col - 1, numRows, numCols)) {
            return true;
        }

        visited[row][col] = false;
        path[row][col] = 0;
        return false;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

