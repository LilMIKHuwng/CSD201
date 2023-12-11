
package array;

public class FindNumbersWithEvenNumberofDigits {
    public static int findNumber(int[] nums){
        int count = 0;
        for (int num : nums) {
            int sochuso = tinhsochuso(num);
            if (sochuso % 2 == 0){
                count++;
            }
        }
        return count;
    }

    private static int tinhsochuso(int num) {
        int count = 0;
        while (num != 0){
            int kq = num/10;
            num = kq;
            count++;
        }
        return count;
    }
    
    public static void main(String[] args) {
        int[] nums = {12,345,2,6,7896};
        System.out.println(findNumber(nums));
    }
}
