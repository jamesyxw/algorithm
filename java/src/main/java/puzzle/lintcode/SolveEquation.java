package puzzle.lintcode;

import java.util.Stack;

/**
 * Solve a given equation and return the value of x in the form of string "x=#value". 
 * The equation contains only '+', '-' operation, the variable x and its coefficient.

    If there is no solution for the equation, return "No solution".
    
    If there are infinite solutions for the equation, return "Infinite solutions".
    
    If there is exactly one solution for the equation, we ensure that the value of x is an integer.
    
    Example 1:
    Input: "x+5-3+x=6+x-2"
    Output: "x=2"
    
    Example 2:
    Input: "x=x"
    Output: "Infinite solutions"
    
    Example 3:
    Input: "2x=x"
    Output: "x=0"
    
    Example 4:
    Input: "2x+3x-6x=x+2"
    Output: "x=-1"
    
    Example 5:
    Input: "x=x+2"
    Output: "No solution"
    
 * @author jamesyxw
 *
 */
public class SolveEquation {
    
    public static void main(String[] args) {
        SolveEquation sol = new SolveEquation();
        System.out.println(sol.solveEquation("x+5-3+x=6+x-2"));
        System.out.println(sol.solveEquation("x=x"));
        System.out.println(sol.solveEquation("2x=x"));
        System.out.println(sol.solveEquation("2x+3x-6x=x+2"));
        System.out.println(sol.solveEquation("x=x+2"));
    }
    
    /**
     * @param equation: a string
     * @return: return a string
     */
    public String solveEquation(String equation) {
        // write your code here
        if(equation == null || equation.isEmpty()) {
            return "";
        }
        
        //split the equation into two parts: left half and right half
        String[] halves = equation.split("[=]");
        int[] left = parse(halves[0]);
        int[] right = parse(halves[1]);
        
//        System.out.println(String.format("left: %s %s, right: %s %s", left[0], left[1], right[0], right[1]));
        
        if (left[0] == right[0] && left[1] != right[1]) {
            return "No solution";
        } else if (left[0] == right[0] && left[1] == right[1]) {
            return "Infinite solutions";
        }
        
        int a = left[0] - right[0];
        int b = right[1] - left[1];
        StringBuilder sb = new StringBuilder();
        sb.append("x=").append(b/a);
        
        return sb.toString();
    }

    private int[] parse(String str) {
        int[] res = new int[2];
        res[0] = 0;
        res[1] = 0;
        
        int i = 0;
        while(i < str.length()) {
            boolean sign = true;
            if (str.charAt(i) == '-') {
                sign = false;
                i++;
            } else if (str.charAt(i) == '+') {
                i++;
            }
            
            if (str.charAt(i) == 'x') {
                res[0] = sign ? res[0] + 1 : res[0] - 1;
                i++;
                continue;
            }
            
            StringBuilder sb = new StringBuilder();
            while(i < str.length() && str.charAt(i) <= '9' && str.charAt(i) >= '0') {
                sb.append(str.charAt(i));
                i++;
            }
            String num = sb.toString();
            int coef = num.isEmpty() ? 1 : Integer.valueOf(num);
            
            if (i < str.length() && str.charAt(i) == 'x') {
                res[0] = sign ? res[0] + coef : res[0] - coef;
                i++;
            } else {
                res[1] = sign ? res[1] + coef : res[1] - coef;
            }
        }
        
        return res;
    }

}
