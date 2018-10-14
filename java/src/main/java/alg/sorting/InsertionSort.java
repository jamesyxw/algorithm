package alg.sorting;

import java.util.*;

public class InsertionSort {

    public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            Comparable valueToInsert = a[i];
            int hole = i;

            while (hole > 0 && a[hole-1].compareTo(valueToInsert) > 0) {
                a[hole] = a[hole-1];
                hole--;
            }

            a[hole] = valueToInsert;
        }
    }

    private static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}