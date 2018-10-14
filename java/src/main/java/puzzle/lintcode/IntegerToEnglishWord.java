package puzzle.lintcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 2^31 - 1
 * 
 *  123 -> "One Hundred Twenty Three"
    12345 -> "Twelve Thousand Three Hundred Forty Five"
    1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * @author jamesyxw
 *
 */
public class IntegerToEnglishWord {
    static Map<Integer, String>  numToWord = new HashMap<Integer, String>();
    
    static {
        numToWord.put(0, "Zero");
        numToWord.put(1, "One");
        numToWord.put(2, "Two");
        numToWord.put(3, "Three");
        numToWord.put(4, "Four");
        numToWord.put(5, "Five");
        numToWord.put(6, "Six");
        numToWord.put(7, "Seven");
        numToWord.put(8, "Eight");
        numToWord.put(9, "Nine");
        numToWord.put(10, "Ten");
        numToWord.put(11, "Eleven");
        numToWord.put(12, "Twelve");
        numToWord.put(13, "Thirteen");
        numToWord.put(14, "Fourteen");
        numToWord.put(15, "Fifteen");
        numToWord.put(16, "Sixteen");
        numToWord.put(17, "SeventTeen");
        numToWord.put(18, "Eighteen");
        numToWord.put(19, "Nineteen");
        numToWord.put(20, "Twenty");
        numToWord.put(30, "Thirty");
        numToWord.put(40, "Forty");
        numToWord.put(50, "Fifty");
        numToWord.put(60, "Sixty");
        numToWord.put(70, "Seventy");
        numToWord.put(80, "Eighty");
        numToWord.put(90, "Ninety");
    }
    
    public static void main(String[] args) {
        
        int[] inputs = {0, 123, 12345, 1234567, 1234567892, Integer.MAX_VALUE, 63808};
        for (int i = 0; i < inputs.length; i++) {
            System.out.println(inputs[i] + ": " + numberToWords(inputs[i]));
        }
    }
    
    
    /**
     * @param num: a non-negative integer
     * @return: english words representation
     */
    public static String numberToWords(int num) {
        if (num == 0) {
            return numToWord.get(num);
        }
        String result = "";
        
        String[] units = {"Thousand", "Million", "Billion"};
        
        int current = num % 1000;
        result = getWordsLessThenOneThousand(current);
        num = (num - current) / 1000;
        
        for (int i = 0; i < units.length; i++) {
            if (num <= 0) {
                break;
            }
            
            current = num % 1000;
            result = getWordsLessThenOneThousand(current) + " " + units[i] + " " + result;
            num = (num - current) / 1000;
        }
        
        return result;
    }

    private static String getWordsLessThenOneThousand(int current) {
        if (current > 999) {
            throw new IllegalArgumentException();
        }
        StringBuilder sb = new StringBuilder();
        if (current > 99) {
            int hundreds = (current - current%100)/100;
            sb.append(numToWord.get(hundreds) + " Hundred");
            current = current % 100;
        }
        
        if (current > 19) {
            int tens = (current - current % 10);
            sb.append(" " + numToWord.get(tens));
            int lastDigit = current % 10;
            if (lastDigit != 0) {
                sb.append(" " + numToWord.get(lastDigit));
            }
        } else {
            int tens = current % 100;
            sb.append(" " + numToWord.get(tens));
        }
        
        return sb.toString().trim();
    }

}
