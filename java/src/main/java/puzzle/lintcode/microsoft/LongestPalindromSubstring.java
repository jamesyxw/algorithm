package puzzle.lintcode.microsoft;

/**
 * 
 * Given a string S, find the longest palindromic substring in S. 
 * You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
 * 
 * Example
    Given the string = "abcdzdcab", return "cdzdc".
    
    Challenge
    O(n2) time is acceptable. Can you do it in O(n) time.
 * 
 * @author jamesyxw
 *
 */
public class LongestPalindromSubstring {
    
    public static void main(String[] args) {
        String[] inputs = {"abcdzdcab", "abcdefkfedcba", "bb", "abb"};
        for (int i = 0; i < inputs.length; i++) {
//            System.out.println(inputs[i] + ": " + longestPalindrome(inputs[i]));
//            System.out.println(inputs[i] + ": " + longestPalindromeDP(inputs[i]));
            System.out.println(inputs[i] + ": " + longestPalindromeManacher(inputs[i]));
        }
    }
    
    public static String longestPalindromeManacher(String s) {
        
        //declare the center of the current longest palindrome and it right boundary R. 
        int c = 0, r = 0;
        //declare a transformed array T that start with '$', ends with '@' and have '#' insert between each char of the input string
        int N = s.length();
        char [] T = new char[2 * N + 3];
        T[0] = '$';
        T[T.length - 1] = '@';
        int fill = 1;
        for (int i = 0; i < N; i++) {
            T[fill++] = '#';
            T[fill++] = s.charAt(i);
        }
        T[T.length - 2] = '#';
        
        //declar a palindrome array, P = [T.length] to keep tracks of the length of parlindrome centered at i, initialize 
        int [] P = new int[T.length];
        for (int i = 0; i < P.length; i++) {
          //the value at all the indexes to 0
            P[i] = 0;
        }
        
        int maxC = 0;
        int maxR = 0;
        //iterate index i from 1 to T.length - 1 trying to find the center of the next palindrome
        for (int i = 1; i < T.length - 1; i++) {
            //mirror for i given c is the center = 2 * c - i
            int mirror = 2 * c - i;
            
            //if i lies within the boundary, R such that i < R; if so, P[i] = minimum of (R - i, or length of parlindrome at mirrow)
            if (i < r) {
                P[i] = Math.min(r - i, P[mirror]);
            }
            
            //expand centered at i, start with comparing i + 1 + P[i] and i - (1 + P[i]), if equal, increment P[i]
            while (T[i + 1 + P[i]] == T[i - (1 + P[i])]) {
                P[i]++;
            }
            
            //check to see if expansion went beyond R, if so, update the center c to i and right boundary R to i + P[i]
            if (i + P[i] > r) {
                c = i;
                r = i + P[i];
            }
            
            if (r - c > maxR - maxC) {
                maxC = c;
                maxR = r;
            }
        }
        
        //compute the left boundary of the current 
        int maxL = 2 * maxC - maxR;
        StringBuilder sb = new StringBuilder();
        for (int i = maxL; i <= maxR; i++) {
            if (i != 0 && i != T.length - 1 && i%2 == 0) {
                sb.append(T[i] + "");
            }
        }
        
        return sb.toString();
    }
    
    //=================================== Time complexity: O(N^2), Space Complexity: O(N^2) ==================================
    
    public static String longestPalindromeDP(String s) {
        if (s == null || s.length() == 1) {
            return s;
        }
        
        int n = s.length();
        
        //table[i][j] represents if the substring from i to j is a palindrome
        boolean table[][] = new boolean[n][n];
        int maxStart = 0;
        int maxEnd = 0;
        int maxLength = 1;
        
        for(int i = 0; i < n; i++) {
            table[i][i] = true;
        }
        
        
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
                table[i][i+1] = true;
                if (maxLength < 2) {
                    maxLength = 2;
                    maxStart = i;
                    maxEnd = i + 1;
                }
            }
        }
        
        //k is the length of the substring
        for (int k = 3; k <= n; k++) {
            for (int i = 0; i < n - k + 1; i++) {
                //ending index;
                int j = i + k - 1;
                
                if (table[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
                    table[i][j] = true;
                    if (k > maxLength) {
                        maxStart = i;
                        maxEnd = j;
                        maxLength = k;
                    }
                }
            }
        }
        
        return s.substring(maxStart, maxEnd + 1);
        
    }
    
    //========================================== Time Complexity: O(N^2), Space Complexity: O(1) =================================
    public static class Interval {
        int start;
        int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        public int length() {
            return this.end - this.start + 1;
        }
    }
    /**
     * @param s: input string
     * @return: the longest palindromic substring
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 1) {
            return s;
        }
        char[] a = s.toCharArray();
        Interval maxPalindrome = new Interval(0, 0);
        for (int i = 0; i < s.length(); i++) {
            Interval oddPalindrome = findOddPalindrome(a, i);
            Interval evenPalindrome = findEvenPalindrom(a, i);
            Interval current = oddPalindrome.length() > evenPalindrome.length() ? oddPalindrome : evenPalindrome;
            maxPalindrome = current.length() > maxPalindrome.length() ? current : maxPalindrome;
        }
        
        return s.substring(maxPalindrome.start, maxPalindrome.end + 1);
    }

    private static Interval findOddPalindrome(char[] a, int i) {
        int step = 1;
        while( i - step >= 0 && i + step < a.length) {
            if(a[i - step] != a[i + step]) {
                break;
            }
            step++;
        }
        step--;
        return new Interval(i - step, i + step);
    }

    private static Interval findEvenPalindrom(char[] a, int i) {
        int step = 1;
        while (i - step + 1 >= 0 && i + step < a.length) {
            if (a[i - step + 1] != a[i + step]) {
                break;
            }
            step++;
        }
        step--;
        return new Interval(i - step + 1, i + step);
    }
}
