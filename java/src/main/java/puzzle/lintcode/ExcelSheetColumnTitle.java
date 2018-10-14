package puzzle.lintcode;

/**
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 *  1 -> A
    2 -> B
    3 -> C
     ...
    26 -> Z
    27 -> AA
    28 -> AB 
 * @author wangy
 *
 */
public class ExcelSheetColumnTitle {
    public static void main(String[] args) {
        for (int i = 0; i < 60; i++) {
            System.out.println("i: " + i + ", title: " + convertToTitle(i));
        }
    }
    
    /**
     * A -- 1 = 26 * 0 + 1
     * B -- 2 = 26 * 0 + 2
     * ...
     * Z -- 26 = 26 * 0 + 26
     * AA -- 27 = 26 * 1 + 1
     * AB -- 28 = 26 * 1 + 2
     * ...
     * AZ -- 52 = 26 * 1 + 26
     * 
     * @param n: a integer
     * @return: return a string
     */
    public static String convertToTitle(int n) {
    	if (n <= 0) {
    		return "";
    	}
    	String result = "";
        int current = n;
        while (current > 0) {
        	int residue = current % 26;
        	if (residue == 0) {
        		result = 'Z' + result;
        		current = current - 26;
        	} else {
        		char letter = (char) ('A' + residue - 1);
        		result = letter + result;
        		current = current - residue;
        	}
        	current = current/26;
        }
        
        return result;
    }
}
