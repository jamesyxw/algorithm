package puzzle.lintcode.amazon;

import java.util.ArrayList;
import java.util.List;

import puzzle.lintcode.common.ListUtils;

/**
 * A string S of lowercase letters is given. 
 * We want to partition this string into as many parts as possible so that each letter appears in at most one part, 
 * and return a list of integers representing the size of these parts.

    1.S will have length in range [1, 500].
    2.S will consist of lowercase letters ('a' to 'z') only.
    
    Have you met this question in a real interview?  
    Example
    Input: S = "ababcbacadefegdehijhklij"
    Output: [9,7,8]
    Explanation:
    The partition is "ababcbaca", "defegde", "hijhklij".
    This is a partition so that each letter appears in at most one part.
    A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
    
 * @author jamesyxw
 *
 */
public class PartitionLabel {
    
    public static void main(String[] args) {
        PartitionLabel sol = new PartitionLabel();
        ListUtils util = new ListUtils();
        util.displayList(sol.partitionLabels("ababcbacadefegdehijhklij"));
    }
    
    /**
     * This method only use O(1) memory. 
     * @param S
     * @return
     */
    public List<Integer> partitionLabels2(String S) {
        List<Integer> res = new ArrayList<Integer>();
        if (S == null || S.isEmpty()) {
            return res;
        }

        int n = S.length();

        //Keep track of the last occurrence of an letter in the label
        int[] last = new int[26];
        for(int i = 0; i < 26; i++) {
            last[i] = -1;
        }
        for(int i = 0; i < n; i++) {
            int pos = S.charAt(i) - 'a';
            last[pos] = i;
        }


        //The invariance of the function loop is that the substring from i to j must satisfy the one occurrence conditions
        //for all the chars in the substring from i to k
        //This allows we use the condition k == j to check if the substring i to j is a partition that satisfies the condition. 
        int i = 0, j = 0;
        for(int k = 0; k < n; k++) {
            int pos = S.charAt(k) - 'a';
            if (last[pos] <= j && k == j) {
                res.add(j-i+1);
                i = k+1;
                j = k+1;
            } else if (last[pos] > j) {

                j = last[pos];
            }
        }

        return res;
    }

    /**
     * This method use O(n) memory, which is not neccessary
     * @param S: a string
     * @return: a list of integers representing the size of these parts
     */
    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<Integer>();
        if (S == null || S.isEmpty()) {
            return res;
        }

        int n = S.length();

        //This array keeps tracks of the last occurrences of the letter at "letter - 'a'" index
        int[] last = new int[26];
        for(int i = 0; i < 26; i++) {
            last[i] = -1;
        }

        //This array keeps track of the next occurrences of the letter at i for each char in S
        //The array is initialized by scanning from right to left in the input String
        int[] next = new int[n];
        for(int i = n - 1; i >= 0; i--) {
            int pos = S.charAt(i) - 'a';
            next[i] = last[pos];
            last[pos] = i;
        }

        //i and j represents the first and last index of the potential subarray that meets the requirement,
        //the invariance is that the substring from i to j should at least satisfy that any char from i to k only occur in the substring from i to j
        //This means if k == j, then we have a substring from i to j that satify the condition to be partition
        //k is the pointer that scan through the String from 0 to n - 1;
        int i = 0, j = 0;
        for(int k = 0; k < n; k++) {
            if (next[k] == -1 && k == j) {
                //k catches up with j and current char at j has no occurrence in the following sequences
                //the substring from i to j is a minimum partition of the input label
                res.add(j-i+1);
                i = k+1;
                j = k+1;
            } else if (next[k] != -1 && next[k] > j) {
                //this means the char at index k has a next occurrence beyond j, need to update j to keep the invariance
                j = next[k];
            }
        }

        return res;
    }
}
