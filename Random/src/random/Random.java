
package random;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Random {


    public static void main(String[] args) {
//        int n = 5000;
//        int[][] matrix = new int[n][n]; // Tạo một ma trận 2x5, tức có 10 phần tử
//        Random rand = new Random();
//    
//        // Điền ma trận với các số ngẫu nhiên từ 0 đến 100
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                matrix[i][j] = rand.nextInt(101); // Số ngẫu nhiên từ 0 đến 100
//            }
//        }
//
//        // Tính tổng của các phần tử trong ma trận
//        int sum = 0;
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                sum += matrix[i][j];
//            }
//        }
        
        // Bắt đầu tính thời gian
        long startTime = System.currentTimeMillis();

        List<Integer> myList = new ArrayList<>();
        Random rand = new Random();

        // Thêm các phần tử ngẫu nhiên từ 0 đến 100 vào danh sách
        for (int i = 0; i < 10000000; i++) { // Thêm 10 phần tử
            int randomNumber = rand.nextInt(101); // Số ngẫu nhiên từ 0 đến 100
            myList.add(randomNumber);
        }

        // Tính tổng của các phần tử trong danh sách
        int sum = 0;
        for (int number : myList) {
            sum += number;
        }

        // Kết thúc tính thời gian
        long endTime = System.currentTimeMillis();

        // In tổng

        System.out.println("Tổng của các phần tử trong danh sách: " + sum);

        // In thời gian thực thi
        long executionTime = endTime - startTime;
        System.out.println("Thời gian thực thi: " + executionTime + " ms");
    }
    
}
