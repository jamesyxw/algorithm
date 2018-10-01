package puzzle.lintcode.amazon;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 * 
 * Example
    For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3.
    
    For "bbbbb" the longest substring is "b", with the length of 1.
    
    Challenge
    O(n) time
    
 * @author jamesyxw
 *
 */
public class LongestSubstringWithoutRepeatingChar {
    
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingChar sol = new LongestSubstringWithoutRepeatingChar();
        
        System.out.println(sol.lengthOfLongestSubstring("bpfbbhmipx"));
        System.out.println(sol.lengthOfLongestSubstring("aaa"));
    }

    /**
     * @param s: a string
     * @return: an integer
     */
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.isEmpty()) {
            return 0;
        }
        
        Map<Character, Integer> index = new HashMap<Character, Integer>();
        
        int max = 0;
        int start = 0;
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (index.containsKey(ch) && index.get(ch) >= start) {
                
                if (max < i - start) {
                    max = i - start;
                }
                
                start = index.get(ch) + 1;
            }
            index.put(ch, i);
        }
        if (max < s.length() - start) {
            return s.length() - start;
        }
        
        return max;
    }
}
