package algorithm.sorting;

public class HeapSort {
    public static void sort(Comparable[] a) {
        int N = a.length;
        heapify(a);
        for (int i = N - 1; i > 0; i--) {
            Utils.swap(a, 0, i);
            bubbleDown(a, 0, i);
        }
    }

    public static void heapify(Comparable[] a) {
        int N = a.length;
        for (int i = (N-1)/2; i >= 0; i--) {
            bubbleDown(a, i, N);
        }
    }

    public static void bubbleDown(Comparable[] a, int i, int N) {
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int max = i;

            if (left >= N && right >= N) {
                break;
            }

            if (left < N && a[left].compareTo(a[i]) > 0) {
                max = left;
            }
            
            if (right < N && a[right].compareTo(a[max]) > 0){
                max = right;
            }


            if (i != max) {
                Utils.swap(a, i, max);
                i = max;
            } else {
                break;
            }
        }
    }
}