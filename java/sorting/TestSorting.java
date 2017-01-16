import java.util.*;

public class TestSorting {
    public static void main(String[] args) {
        Comparable[] test = generateRandIntArray(10, 20);
        display(test);

        Comparable[] insertionCopy = test.clone();
        InsertionSort.sort(insertionCopy);
        display(insertionCopy);
        System.out.println(validate(insertionCopy));

        Comparable[] quickCopy = test.clone();
        QuickSort.sort(quickCopy);
        display(quickCopy);
        System.out.println(validate(quickCopy));

        Comparable[] mergeCopy = test.clone();
        MergeSort.sort(mergeCopy);
        display(mergeCopy);
        System.out.println(validate(mergeCopy));

    }

    public static void display(Comparable[] a){
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

	private static boolean validate(Comparable[] array) {
		for (int i = 1; i < array.length; i++) {
			if (array[i].compareTo(array[i-1]) < 0) {
				return false;
			}
		}
		return true;
	}

	private static Integer[] generateRandIntArray(int count, int max) {
		Integer[] a = new Integer[count];
		Random random = new Random();

        for(int i = 0; i < count; i++){
            a[i] = random.nextInt(max);
        }

		return a;
	}
}