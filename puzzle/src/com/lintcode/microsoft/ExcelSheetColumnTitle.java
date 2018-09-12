package com.lintcode.microsoft;

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
        for (int i = 0; i < 28; i++) {
            System.out.println("i: " + i + ", title: " + convertToTitle(i));
        }
    }
    
    /**
     * @param n: a integer
     * @return: return a string
     */
    public static String convertToTitle(int n) {
        if (n <= 0) {
            return null;
        }
        // write your code here
        String title = "";
        int current = n;
        while (current > 0) {
            int residue = current%26;
            char letter = convertIntToLetter(residue);
            System.out.println(letter + "  " + residue);
            title = letter + title;
            current = (current - residue)/26;
        }
        
        return title;
    }

    private static char convertIntToLetter(int residue) {
        return (char) ('A' + residue - 1);
    }
}
