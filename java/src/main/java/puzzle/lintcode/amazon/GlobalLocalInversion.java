package puzzle.lintcode.amazon;

/**
 * We have some permutation A of [0, 1, ..., N - 1], where N is the length of A.
    The number of (global) inversions is the number of i < j with 0 <= i < j < N and A[i] > A[j].
    The number of local inversions is the number of i with 0 <= i < N and A[i] > A[i+1].
    Return true if and only if the number of global inversions is equal to the number of local inversions.
    
    1.A will be a permutation of [0, 1, ..., A.length - 1].
    2.A will have length in range [1, 5000].
    3.The time limit for this problem has been reduced.
    
    Have you met this question in a real interview?  
    
    Example 1:
    Input: A = [1,0,2]
    Output: true
    Explanation: There is 1 global inversion, and 1 local inversion.
    
    Example 2:
    Input: A = [1,2,0]
    Output: false
    Explanation: There are 2 global inversions, and 1 local inversion.
    
 * @author jamesyxw
 *
 */
public class GlobalLocalInversion {
    public static void main(String[] args) {
        GlobalLocalInversion sol = new GlobalLocalInversion();
        int[] inputs = {1, 0, 2};
        System.out.println(sol.isIdealPermutation(inputs));
        System.out.println(sol.isIdealPermutation_Smart(inputs));
        int[] inputs2 = {1, 2, 0};
        System.out.println(sol.isIdealPermutation(inputs2));
        System.out.println(sol.isIdealPermutation_Smart(inputs2));
        int[] inputs3 = {2, 0, 1, 3};
        System.out.println(sol.isIdealPermutation(inputs3));
        System.out.println(sol.isIdealPermutation_Smart(inputs3));
    }
    
    /**
     * Basically if current max is larger than the item at i + 2, then it's non ideal
     * @param A
     * @return
     */
    public boolean isIdealPermutation_Smart(int[] A) {
        int n = A.length;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n - 2; i++) {
            max = Math.max(max, A[i]);
            if(max > A[i+2]) {
                return false;
            }
        }
        
        return true;
        
    }
    /**
     * @param A: an array
     * @return: is the number of global inversions is equal to the number of local inversions
     */
    public boolean isIdealPermutation(int[] A) {
        // Write your code here
        int n = A.length;
        int[] min = new int[n]; //record the min from n-1 to i at min[i];
        int curMin = Integer.MAX_VALUE;
        for(int i = n - 1; i >= 0; i--) {
            if(curMin > A[i]) {
                curMin = A[i];
            }
            min[i] = curMin;
        }
        
        int global = 0;
        int local = 0;
        
        for(int i = 0; i < n - 1; i++) {
            int j = i + 1;
            while(j < n && A[i] > min[j]) {
                if (A[i] > A[j]) {
                    global++;
                }
                j++;
            }
            
            if(A[i] > A[i+1]) {
                local++;
            }
        }
        
        return global==local;
    }
}
