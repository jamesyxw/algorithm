package sorting;

public class Utils {
    public static void swap (Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

}