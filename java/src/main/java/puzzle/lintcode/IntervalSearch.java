package puzzle.lintcode;

import java.util.List;

/**
 * Given a List of intervals, the length of each interval is 1000, such as [500,1500], [2100,3100].
 * Give a number arbitrarily and determine if the number belongs to any of the intervals.return True or False.
 * 
 * @author jamesyxw
 *
 */
public class IntervalSearch {

	/**
     * @param intervalList: 
     * @param number: 
     * @return: return True or False
     */
    public String isInterval(List<List<Integer>> intervalList, int number) {
        for(List<Integer> interval : intervalList) {
        	if (number >= interval.get(0) && number <= interval.get(1)) {
        		return "True";
        	}
        }
        
        return "False";
    }
}
