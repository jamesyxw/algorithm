package puzzle.lintcode;

import java.util.Arrays;

/**
 * We have two integer sequences A and B of the same non-zero length.

    We are allowed to swap elements A[i] and B[i]. 
    Note that both elements are in the same index position in their respective sequences.
    
    At the end of some number of swaps, A and B are both strictly increasing. 
    (A sequence is strictly increasing if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)
    
    Given A and B, return the minimum number of swaps to make both sequences strictly increasing. 
    It is guaranteed that the given input always makes it possible.
    
    1.A, B are arrays with the same length, and that length will be in the range [1, 1000].
    2.A[i], B[i] are integer values in the range [0, 2000].
    
    
    Example
    Input: A = [1,3,5,4], B = [1,2,3,7]
    Output: 1
    Explanation: 
    Swap A[3] and B[3].  Then the sequences are:
    A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
    which are both strictly increasing.
    
 * @author jamesyxw
 *
 */
public class MinSwapToMakeSequenceIncreasing {
    
    public static void main(String[] args) {
        MinSwapToMakeSequenceIncreasing sol = new MinSwapToMakeSequenceIncreasing();
        int[] A = {1, 3, 5, 4};
        int[] B = {1, 2, 3, 7};
        
        System.out.println(sol.minSwap(A, B));
    }
    /**
     * @param A: an array
     * @param B: an array
     * @return: the minimum number of swaps to make both sequences strictly increasing
     */
    public int minSwap(int[] A, int[] B) {
        int n = A.length;
        
        //swaps[i] represent the minimum number of swaps to makes both sequences strictly increasing if swap happens at i
        int[] swaps = new int[n];
        //noswaps[i] represent the minimum number of swaps to makes both sequences strictly increasing if swap doesn't happens at i
        int[] noswaps = new int[n];
        
        Arrays.fill(swaps, n);
        Arrays.fill(noswaps, n);
        
        swaps[0] = 1;
        noswaps[0] = 0;
        
        for (int i = 1; i < n; i++) {
            //if no swap needed at i and 0 to i are already strictly increasing
            //this means A[i] > A[i-1] && B[i] > B[i-1]
            if (A[i] > A[i-1] && B[i] > B[i-1]) {
                //swaps together
                swaps[i] = swaps[i-1] + 1;
                //or no swaps together
                noswaps[i] = noswaps[i-1];
            }
            
            //if swap is needed at i
            //this means A[i] > B[i-1] && B[i] > A[i-1]
            if (A[i] > B[i-1] && B[i] > A[i-1]) {
                //either i swaps and i - 1 stays
                swaps[i] = Math.min(swaps[i], noswaps[i-1] + 1);
                //or i stays and i - 1 swaps
                noswaps[i] = Math.min(noswaps[i], swaps[i-1]);
            }
        }
        
        
        return Math.min(swaps[n-1], noswaps[n-1]);
    }
}
