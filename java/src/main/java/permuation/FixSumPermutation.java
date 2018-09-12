package permuation;

import java.util.ArrayList;
import java.util.List;

public class FixSumPermutation {
    
    public static void main(String[] args) {
        int[] test = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        permutation(10, test, 6);
    }
    
    public static void permutation(int sum, int[] numbers, int count) {
        int[] partial = new int[0];
        permutate(numbers, sum, partial, count);
    }
    
    private static  int sum (int[] numbers) {
        int result = 0;
        for (int i = 0; i < numbers.length; i++) {
            result += numbers[i];
        }
        return result;
    }
    
    private static  void display(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + ",");
        }
        System.out.println("");
    }
    
    private static  void permutate(int[] numbers, int target, int[] partial, int count) {
        int partialSum = sum(partial);
        
        if (partialSum == target) {
            display(partial);
        } else if (partial.length == count) {
            return;
        } else{
            for (int i = 0; i < numbers.length; i++) {
                int n = numbers[i];
                int m = partial.length;
                int[] newPartial = new int[m + 1];
                for(int j = 0; j < m; j++) {
                    newPartial[j] = partial[j];
                }
                newPartial[m] = n;
                permutate(numbers, target, newPartial, count);
            }
        }
    }
    
    
}
