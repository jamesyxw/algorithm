package puzzle.lintcode;

import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * 
 * Example
    The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
    
    Challenge
    O(n)的时间，n为括�?�的个数

 * @author jamesyxw
 *
 */
public class ValidParentheses {
    
    public static void main(String[] args) {
        ValidParentheses sol = new ValidParentheses();
        System.out.println(sol.isValidParentheses("()"));
        System.out.println(sol.isValidParentheses("{}"));
        System.out.println(sol.isValidParentheses("[]"));
        System.out.println(sol.isValidParentheses("["));
        System.out.println(sol.isValidParentheses("]"));
    }

    /**
     * @param s: A string
     * @return: whether the string is a valid parentheses
     */
    public boolean isValidParentheses(String s) {
        if(s == null || s.isEmpty()) {
            return true;
        }
        
        Stack<Character> stack = new Stack<Character>();
        for(int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (stack.isEmpty()) {
                stack.push(cur);
            }else if ((cur == ')' && stack.peek().equals('('))
                    || (cur == '}' && stack.peek().equals('{'))
                    || (cur == ']' && stack.peek().equals('['))) {
                stack.pop();
            } else {
                stack.push(cur);
            }
        }
        
        return stack.isEmpty();
    }
}
