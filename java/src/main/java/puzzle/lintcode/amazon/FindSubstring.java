package puzzle.lintcode.amazon;

import java.util.HashSet;
import java.util.Set;

/**
 * Given the length k, find all substrings of length k in the string str.
 * The characters of a substring can not be repeated and output the number of substrings that satisfy such conditions 
 * (the same substring is counted only 1 times).
 * 
 * |str| <= 100000.
    k <= 100000.
    All characters are lowercase.
 * 
 * 
 * Example
    Given str = "abc", k = 2, output 2.
    
    Explanation:
    Characters are not repeated, and substrings of length k have "ab", "bc".
    Given str = "abc", k = 2, output 2.
    
    Explanation:
    Characters are not repeated, and substrings of length k have "a", "b".�c�.
    
 * @author jamesyxw
 *
 */
public class FindSubstring {
    
    public static void main(String[] args) {
        FindSubstring sol = new FindSubstring();
        System.out.println(sol.findSubstring("abccef", 2));
        System.out.println(sol.findSubstring("abc", 2));
        System.out.println(sol.findSubstring("abccef", 3));
    }
    /**
     * @param str: The string
     * @param k: The length of the substring
     * @return: The answer
     */
    public int findSubstring(String str, int k) {
        if(str == null) {
            return 0;
        }
        
        if(k == 0) {
            return 1;
        }

        //last is an array of size 26, used to keep track of the last occurrence of a char from 'a' to 'z'
        //'a' -> 0, 'b' -> 1 and so on
        int[] last = new int[26];
        for(int i = 0; i < 26; i++) {
            //initialize all the last occurrence index to -1
            last[i] = -1;
        }
        
        //a hash set is used to remove duplicates
        Set<String> set = new HashSet<String>();

        //i and j represent the first and last index of current substring, 
        int i = 0, j = 0; 
        while (j < str.length()) {
            int index = str.charAt(j) - 'a';

            if(last[index] >= i) {
                i = last[index] + 1;
            }

            if (j - i + 1 == k) {
                set.add(str.substring(i, j + 1));
                i++;
            }

            //update the last occurrence index of char at j in str
            last[index] = j;
            j++;
        }
        
        return set.size();
    }
}
