package com.leetcode.array;

import java.util.List;

public class ArrayUtil {
    public static int[] convertToArray(List<Integer> inputs) {
        int[] array = new int[inputs.size()];
        for(int i = 0; i < inputs.size(); i++) {
            array[i] = inputs.get(i);
        }
        return array;
    }
    
    public static <T> void display(List<List<T>> inputs) {
        for (List<T> tuple : inputs) {
            for (T num : tuple) {
                System.out.print(num.toString() + " ");
            }
            System.out.println();
        }
    }
    
    public static void display(int[] inputs) {
        for(int i = 0; i < inputs.length; i++) {
            System.out.print(inputs[i] + " ");
        }
        System.out.println();
    }
    
    public static void swap(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
}
