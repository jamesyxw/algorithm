package sorting;

import java.util.*;

public class QuickSort {
    public static void sort(Comparable[] a){
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int low, int high){
        if (high <= low){
            return;
        }

        int j = partition(a, low, high);
        sort(a, low, j - 1);
        sort(a, j + 1, high);
    }

    private static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    private static int partition(Comparable[] a, int low, int high){
        int i = low;
        int j = high + 1;
        Comparable v = a[low];

        while(true){
            while(less(a[++i], v)){
                if(i == high) break;
            }

            while(less(v, a[--j])){
                if(j == low) break;
            }

            if(i >= j) {
                break;
            }

            swap(a, i, j);
        }

        //put the pivot item low at j
        swap(a, low, j);

        //a[low ... j - 1] <= a[j] <= a[j + 1 ... high]
        return j;
    }

    private static void swap(Object[] a, int i, int j){
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}



