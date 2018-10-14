package algorithm.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import alg.sorting.HashHeap;

public class HashHeapTest {

    @Test
    public void testHashHeap() {
        HashHeap minHeap = new HashHeap("min");
        
        List<Integer> arr = Arrays.asList(4, 3, 4, 0, 2 , 0, 2, 5, 4, 1);
        
        System.out.println(Arrays.toString(arr.toArray()));
        
        for (Integer item : arr) {
            minHeap.add(item);
        }
        
        System.out.println(minHeap.metadata());
        
        minHeap.delete(3);
        System.out.println(minHeap.metadata());
        minHeap.delete(5);
        System.out.println(minHeap.metadata());
        
        List<Integer> result = new ArrayList<Integer>();
        
        while (!minHeap.empty()) {
            result.add(minHeap.poll());
        }
        
        System.out.println(Arrays.toString(result.toArray()));
    }
}
