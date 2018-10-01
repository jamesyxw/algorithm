package puzzle.lintcode.microsoft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import puzzle.lintcode.common.ArrayUtils;
import puzzle.lintcode.common.ListUtils;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
 * Find all unique triplets in the array which gives the sum of zero.
 * 
 * Example
    For example, given array S = {-1 0 1 2 -1 -4}, A solution set is:
    
    (-1, 0, 1)
    (-1, -1, 2)

 * @author jamesyxw
 *
 */
public class ThreeSum {
    
    public static void main(String[] args) {
        ListUtils<Integer> utils = new ListUtils<Integer>();
        int[][] inputs = {
//                {-1, 0, 1, 2, -1, -4},
//                {1,0,-1,-1,-1,-1,0,1,1,1},
                {-2,-3,-4,-5,-100,99,1,4,4,4,5,1,0,-1,2,3,4,5}
        };
        
        for (int i = 0; i < inputs.length; i++) {
            ArrayUtils.display(inputs[i]);
            List<List<Integer>> result = threeSum(inputs[i]);
            utils.displayListList(result);
        }
    }
    
    /**
     * @param numbers: Give an array numbers of n integer
     * @return: Find all unique triplets in the array which gives the sum of zero.
     */
    public static List<List<Integer>> threeSum(int[] numbers) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        Arrays.sort(numbers);
        ArrayUtils.display(numbers);
        if (numbers[0] > 0) {
            return result;
        }
        
        for (int i = 0; i < numbers.length - 2; i++) {
            if (i > 0 && numbers[i] == numbers[i - 1]) {
                continue;
            }
            
            int x = i + 1;
            int y = numbers.length - 1;
            
            while (x < y) {
                System.out.println(String.format("numbers[%s]=%s, numbers[%s]=%s, numbers[%s]=%s",x, numbers[x], y, numbers[y], i, numbers[i]));
                if (numbers[x] + numbers[y] + numbers[i] == 0) {
                    List<Integer> triplet = new ArrayList<Integer>();
                    triplet.add(numbers[i]);
                    triplet.add(numbers[x]);
                    triplet.add(numbers[y]);
                    result.add(triplet);
                    
                    System.out.println(String.format("{%s, %s, %s}", numbers[x], numbers[y], numbers[i]));
                    
                    x++;
                    y--;
                    
                    while(x < y && numbers[x] == numbers[x-1]) {
                        x++;
                    }
                    
                    while(y > x && numbers[y] == numbers[y+1]) {
                        y--;
                    }
                    
                } else if (numbers[x] + numbers[y] + numbers[i] < 0) {
                    x++;
                } else {
                    y--;
                }
            }
            
        }
        
        return result;
    }

    //merge sort
    private static void sort(int[] numbers) {
        int[] temp = new int[numbers.length];
        mergeSort(numbers, temp, 0, numbers.length - 1);
    }

    private static void mergeSort(int[] numbers, int[] temp, int i, int j) {
        if (i < j) {
            //find the middle
            int mid = i + (j - i)/2;
            //mergesort the left half
            mergeSort(numbers, temp, i, mid);
            //mergesort the right half;
            mergeSort(numbers, temp, mid + 1, j);
            //merge index i to j from numbers to temp
            merge(numbers, temp, i, j, mid);
        }
    }

    private static void merge(int[] numbers, int[] temp, int start, int end, int mid) {
        int i = start;
        int j = mid + 1;
        
        int k = start;
        while (i <= mid && j <= end) {
            if (numbers[i] < numbers[j]) {
                temp[k++] = numbers[i++];
            } else {
                temp[k++] = numbers[j++];
            }
        }
        
        while(i <= mid) {
            temp[k++] = numbers[i++];
        }
        
        while(j <= end) {
            temp[k++] = numbers[j++];
        }
        
        for (int x = start; x <= end; x++) {
            numbers[x] = temp[x];
        }
        
    }
}
