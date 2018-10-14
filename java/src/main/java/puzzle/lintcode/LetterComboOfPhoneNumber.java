package puzzle.lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import puzzle.lintcode.common.ListUtils;

/**
 * Given a digit string excluded 01, return all possible letter combinations that the number could represent.

    A mapping of digit to letters (just like on the telephone buttons) is given below.
    
    Although the above answer is in lexicographical order, your answer could be in any order you want.

    Example
    Given "23"
    
    Return ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]

 * @author jamesyxw
 *
 */
public class LetterComboOfPhoneNumber {
    
//    List<Set<Character>> keys = new ArrayList<Set<Character>>();
    
    public LetterComboOfPhoneNumber() {
//        keys.add(new HashSet<Character>());
//        keys.add(new HashSet<Character>());
//        keys.add(new HashSet<Character>(Arrays.asList('a', 'b', 'c')));
//        keys.add(new HashSet<Character>(Arrays.asList('d', 'e', 'f')));
//        keys.add(new HashSet<Character>(Arrays.asList('g', 'h', 'i')));
//        keys.add(new HashSet<Character>(Arrays.asList('j', 'k', 'l')));
//        keys.add(new HashSet<Character>(Arrays.asList('m', 'n', 'o')));
//        keys.add(new HashSet<Character>(Arrays.asList('p', 'q', 'r', 's')));
//        keys.add(new HashSet<Character>(Arrays.asList('t', 'u', 'v')));
//        keys.add(new HashSet<Character>(Arrays.asList('w', 'x', 'y', 'z')));
        
    }
    
    public static void main(String[] args) {
        ListUtils<String> util = new ListUtils<String>();
        
        LetterComboOfPhoneNumber sol = new LetterComboOfPhoneNumber();
        util.displayList(sol.letterCombinations("23"));
    }
    
    
    /**
     * @param digits: A digital string
     * @return: all posible letter combinations
     */
    public List<String> letterCombinations(String digits) {
        // write your code here
        List<String> res = new ArrayList<String>();
        if (digits == null || digits.isEmpty()) {
            return res;
        }
        
        String[] phone = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        
        dfs(digits, res, phone, "", 0);
        return res;
    }

    private void dfs(String digits, List<String> res, String[] phone, String prefix, int i) {
        int digit = digits.charAt(i) - '0';
        
        if (i == digits.length() - 1) {
            for(int j = 0; j < phone[digit].length(); j++) {
                res.add(prefix + phone[digit].charAt(j));
            }
            return;
        }
        
        for(int j = 0; j < phone[digit].length(); j++) {
            dfs(digits, res, phone, prefix + phone[digit].charAt(j), i + 1);
        }
    }
}
