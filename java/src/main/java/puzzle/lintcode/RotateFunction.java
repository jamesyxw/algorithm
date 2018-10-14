package puzzle.lintcode;

/**
 * Given an array of integers A and let n to be its length.

    Assume Bk to be an array obtained by rotating the array A k positions clock-wise, we define a "rotation function" F on A as follow:

    F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].

    Calculate the maximum value of F(0), F(1), ..., F(n-1).
    
    n is guaranteed to be less than 10^5.
    
    Example
    A = [4, 3, 2, 6]
    
    F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
    F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
    F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
    F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
    
    So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.


 * @author jamesyxw
 *
 */
public class RotateFunction {
    
    public static void main(String[] args) {
        RotateFunction sol = new RotateFunction();
        int[] A = {4,3,2,6};
        System.out.println(sol.maxRotateFunction(A));
        System.out.println(sol.maxRotateFunction2(A));
    }
    
    /**
     * Assume the array is [A, B, C, D]
     * Then we got:
     * F(0) = 0A + 1B + 2C + 3D;
     * F(1) = 0D + 1A + 2B + 3C;
     * F(2) = 0C + 1D + 2A + 3B;
     * F(3) = 0B + 1C + 2D + 3A;
     * 
     * Thus, F(i) = F(i-1) + sum - n * A[n - i]
     * 
     * @param A
     * @return
     */
    public int maxRotateFunction2(int[] A) {
        int sum = 0;
        int f = 0;
        int n = A.length;
        for(int i = 0; i < A.length; i++) {
            sum += A[i];
            f += i * A[i];
        }
        
        int max = f; //this is f(0)
        for(int i = 1; i < A.length; i++) {
            f = f + sum - n * A[n - i];
            max = Math.max(max, f);
        }
        
        return max;
    }
    
    /**
     * This method compute the value of F(0) to F(n-1) and find the max
     * @param A
     * @return
     */
    public int maxRotateFunction(int[] A) {
        int n = A.length;
        if (n == 0) {
            return -1;
        }
        
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, rotate(A, i));
        }
        
        return max;
    }

    /**
     * This method compute the value of F(i) by first compute the value from 0 to A.length - i - 1, then from A.length - i to A.length
     * @param A
     * @param i
     * @return
     */
    private int rotate(int[] A, int i) {
        int res = 0;
        for(int j = 0; j < A.length - i; j++) {
            res += A[j] * (j + i);
        }
        
        for(int j = A.length - i; j < A.length; j++) {
            res += A[j] * (j + i - A.length);
        }
        return res;
    }
}
