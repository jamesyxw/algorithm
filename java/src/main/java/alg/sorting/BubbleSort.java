package alg.sorting;

public class BubbleSort {
    public static void sort (Comparable[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (a[j].compareTo(a[j+1]) > 0) {
                    Utils.swap(a, j, j + 1);
                }
            }
        }
    }
}