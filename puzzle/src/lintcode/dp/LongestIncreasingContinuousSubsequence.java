package lintcode.dp;

/**
 * Give an integer array£¬find the longest increasing continuous subsequence in this array.

    An increasing continuous subsequence:
    
    Can be from right to left or from left to right.
    Indices of the integers in the subsequence should be continuous.
     Notice
    
    O(n) time and O(1) extra space.

 * @author wangy
 *
 */
public class LongestIncreasingContinuousSubsequence {
    
    public static void main(String[] args) {
        int[] arrays1 = {5, 4, 2, 1, 3};
        int[] arrays2 = {5, 1, 2, 3, 4};
        int[] arrays3 = {1, 2};
        int[] arrays4 = {1, 3, 2};
        int[] arrays5 = {3, 2, 1};
        
        LongestIncreasingContinuousSubsequence a = new LongestIncreasingContinuousSubsequence();
        System.out.println("the LIS for arrays1 is " + a.longestIncreasingContinuousSubsequence(arrays1));
        System.out.println("the LIS for arrays2 is " + a.longestIncreasingContinuousSubsequence(arrays2));
        System.out.println("the LIS for arrays3 is " + a.longestIncreasingContinuousSubsequence(arrays3));
        System.out.println("the LIS for arrays4 is " + a.longestIncreasingContinuousSubsequence(arrays4));
        System.out.println("the LIS for arrays5 is " + a.longestIncreasingContinuousSubsequence(arrays5));
    }
    /**
     * @param A an array of Integer
     * @return  an integer
     */
    public int longestIncreasingContinuousSubsequence(int[] A) {
        
        if (A.length == 0) {
            return 0;
        }
        //let n be the length of the longest increasing subsequence in the array from 0 to i;
        //for array from 0 to i + 1, the length of the longest increasing subsequence in the
        //array would n = A[i + 1] > A[i] ? n + 1 : n;
        int n = 1;
        int l2rMax = n;
        for (int i = 1; i < A.length; i++) {
            n = A[i] > A[i-1] ? n + 1: 1;
            if (n > l2rMax) {
                l2rMax = n;
            }
        }
        
        n = 1;
        int r2lMax = n;
        for (int i = A.length - 2; i >= 0; i--) {
            n = A[i] > A[i + 1] ? n + 1: 1;
            if (n > r2lMax) {
                r2lMax = n;
            }
        }
        
        return Math.max(l2rMax, r2lMax);
    }
    
}
