package puzzle.lintcode;

import java.util.ArrayList;
import java.util.List;

import puzzle.lintcode.common.ListUtils;

/**
 * The gray code is a binary numeral system where two successive values differ in only one bit.

    Given a non-negative integer n representing the total number of bits in the code, 
    find the sequence of gray code. A gray code sequence must begin with 0 and with cover all 2n integers.
    
    For a given n, a gray code sequence is not uniquely defined.

    [0,2,3,1] is also a valid gray code sequence according to the above definition.

    Example
    Given n = 2, return [0,1,3,2]. Its gray code sequence is:
    
    00 - 0
    01 - 1
    11 - 3
    10 - 2
    Challenge
    O(2n) time.

 * @author jamesyxw
 *
 */
public class GrayCode {
    public static void main(String [] args) {
        GrayCode sol = new GrayCode();
        ListUtils<Integer> utils = new ListUtils<Integer>();
        utils.displayList(sol.grayCode(3));
        utils.displayList(sol.grayCodeBit(3));
    }

    /**
     * 
     * bitwise operation 
     * 
     * @param n: a number
     * @return: Gray code
     */
    public List<Integer> grayCodeBit(int n) {
        List<Integer> result = new ArrayList<Integer>();
        int num = 0;
        for (int i = 0; i < (1 << n); i++) {
            result.add(num ^ (num >> 1));
            num++;
        }
        
        return result;
    }
    
    /**
     * n-bit gray code can be generated from list of (n-1)-bit gray codes using the following steps:
     * 1. let the list of (n-1)-bit gray codes be L1. create another list L2 which is reverse of L2.
     * 2. Modify the list L1 by prefixing a "0" in all code of L1;
     * 3. Modify the list L2 by prefixing a "1" in all code of L2;
     * Concatenant L1 and L2. The concatenanted list is required list of n-bit gray code
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<Integer>();
        if (n == 0) {
            res.add(0);
            return res;
        }
        
        String[] nums = new String[1 << n];
        
        nums[0] = "0";
        nums[1] = "1";
        
        for(int i = 2; i < (1 << n); i = i << 1) {
            
            //first add the existing elements in res in reverse order and prefix it with "1"
            for(int x = i - 1, y = i; x >= 0 && y < 2 * i; x--, y++) {
                nums[y] = "1" + nums[x];
            }
            
            for(int x = 0; x < i; x++) {
                nums[x] = "0" + nums[x];
            }
        }
        
        
        for(int i = 0; i < nums.length; i++) {
            res.add(bin2dec(nums[i]));
        }
        return res;
    }

    private Integer bin2dec(String bin) {
        int dec = 0;
        for (int i = 0; i < bin.length(); i++) {
            if (bin.charAt(i) == '1') {
                dec += Math.pow(2, bin.length() - i - 1);
            }
        }
        return dec;
    }

}
