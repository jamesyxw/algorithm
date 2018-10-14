package alg.sorting;

import java.util.*;

public class TestSorting {
    public static void main(String[] args) {
        run(10);
    }

    private static void run(int count) {
        for (int j = 0; j < count; j++) {
            for (int i = 1; i < 10; i++) {
                Comparable[] test = generateRandIntArray(i, 20);
                display(test);
                runTest(test);
            }
        }
    }

    private static void runTest(Comparable[] test) {
        try {
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

            Comparable[] heapSortCopy = test.clone();
            HeapSort.sort(heapSortCopy);
            display(heapSortCopy);
            System.out.println(validate(heapSortCopy));

            Comparable[] bubbleCopy = test.clone();
            BubbleSort.sort(bubbleCopy);
            display(bubbleCopy);
            System.out.println(validate(bubbleCopy));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public static void display(Comparable[] a){
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

	private static boolean validate(Comparable[] array) throws Exception{
		for (int i = 1; i < array.length; i++) {
			if (array[i].compareTo(array[i-1]) < 0) {
				throw new Exception("Wrong results");
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