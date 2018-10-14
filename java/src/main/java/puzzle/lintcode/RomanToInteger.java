package puzzle.lintcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a roman numeral, convert it to an integer.

	The answer is guaranteed to be within the range from 1 to 3999.
	
	IV -> 4

	XII -> 12
	
	XXI -> 21
	
	XCIX -> 99

 * @author jamesyxw
 *
 */
public class RomanToInteger {
    
    public static void main(String[] args) throws Exception {
        String[] inputs = {"IV", "XII", "XXI", "MDCCLXXVI", "MCMLIV"};
        for (int i = 0; i < inputs.length; i++ ) {
            System.out.println(inputs[i] + ": " + romanToInt(inputs[i]));
        }
    }
	/**
     * @param s: Roman representation
     * @return: an integer
	 * @throws Exception 
     */
    public static int romanToInt(String s) throws Exception {
        // write your code here
    	int result = 0;
    	Map<Character, Integer> map = new HashMap<Character, Integer>();
    	map.put('I', 1);
    	map.put('V', 5);
    	map.put('X', 10);
    	map.put('L', 50);
    	map.put('C', 100);
    	map.put('D', 500);
    	map.put('M', 1000);
    	
    	char[] sArray = s.toCharArray();
    	int index = 0;
    	while (index < s.length()) {
    	    if (!map.containsKey(sArray[index])) {
    	        throw new Exception();
    	    }
    	    //if the value at (index) is smaller than (index + 1), treat them as a map[index+1] - map[index]
    	    if (index + 1 < s.length()){
    	        if (!map.containsKey(sArray[index + 1])) {
    	            throw new Exception();
    	        } else if (map.get(sArray[index]) < map.get(sArray[index + 1])) {
    	                result += (map.get(sArray[index+1]) - map.get(sArray[index]));
    	                index += 2;
    	                continue;
    	        }
    	    }
    	    
    	    result += map.get(sArray[index]);
            index++;
    	}
    	
    	return result;
    	
    }
}
