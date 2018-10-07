package puzzle.lintcode.amazon;

import java.util.Arrays;
import java.util.List;

import puzzle.lintcode.common.ListUtils;

/**
 * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.

    1.Note that the row index starts from 0.
    2.In Pascal's triangle, each number is the sum of the two numbers directly above it.
    
    Example:
    
    Input: 3
    Output: [1,3,3,1]
    Challenge
    Could you optimize your algorithm to use only O(k) extra space?

 * @author jamesyxw
 *
 */
public class PascalTriangle {

    public static void main(String[] args) {
        PascalTriangle sol = new PascalTriangle();
        ListUtils<Integer> util = new ListUtils<Integer>();
        util.displayList(sol.pascalTriangle(3));
        util.displayList(sol.pascalTriangle(5));
        util.displayList(sol.pascalTriangle(4));
    }

    public List<Integer> pascalTriangle(int k) {
        Integer[] res = new Integer[k + 1];
        if(k == 0) {
            res[0] = 1;
            return Arrays.asList(res);
        }

        if (k == 1) {
            res[0] = 1;
            res[1] = 1;
            return Arrays.asList(res);
        }

        res[0] = 1;
        res[1] = 1;

        for(int i = 2; i <= k; i++) {
            //compute from mid to 1
            int mid = i/2;
            for(int j = mid; j > 0; j--) {
                res[j] = res[j] + res[j-1];
            }

            //mirror the left to the right
            for(int j = 0; j <= mid; j++) {
                res[i-j] = res[j];
            }
        }

        return Arrays.asList(res);
    }
}
