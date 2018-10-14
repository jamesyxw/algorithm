package puzzle.lintcode;

/**
 * Given an input character array, reverse the array word by word. A word is defined as a sequence of non-space characters.
   The input character array does not contain leading or trailing spaces and the words are always separated by a single space.

	Example
	Given s = "the sky is blue",
	after reversing : "blue is sky the"
	
	Challenge
	Could you do it in-place without allocating extra space?

 * @author jamesyxw
 *
 */
public class ReverseWordsInAStringII {
	
	public static void main(String[] args) {
		char[] testStr = "the sky is blue".toCharArray();
		char[] reversedTestStr = reverseWords(testStr);
		
		System.out.println(String.valueOf(reversedTestStr));
	}
	

	/**
     * @param str: a string
     * @return: return a string
     */
    public static char[] reverseWords(char[] str) {
    	if (str.length == 0 || str.length == 1) {
    		return str;
    	}
    	reverse(str, 0, str.length - 1);
    	
    	int wordStart = 0;
    	int wordEnd = 0;
    	
    	while(wordStart < str.length) {
    		while(wordEnd < str.length && str[wordEnd] != ' ') {
    			wordEnd++;
    		}
    		reverse(str, wordStart, wordEnd-1);
    		wordStart = wordEnd + 1;
    		wordEnd = wordStart;
    	}
    	
    	return str;
    }
    
    private static void reverse(char[] str, int start, int end) {
    	while(start < end) {
    		char temp = str[start];
    		str[start] = str[end];
    		str[end] = temp;
    		start++;
    		end--;
    	}
    }
}
    
    
