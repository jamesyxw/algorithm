package puzzle.lintcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import puzzle.lintcode.common.Interval;

/**
 * 
 * Given intervals => merged intervals:

	[                     [
	  (1, 3),               (1, 6),
	  (2, 6),      =>       (8, 10),
	  (8, 10),              (15, 18)
	  (15, 18)            ]
	]
	Challenge
	O(n log n) time and O(1) extra space.


 * @author jamesyxw
 *
 */
public class MergeInterval {

	/**
     * @param intervals: interval list.
     * @return: A new interval list.
     */
    public List<Interval> merge(List<Interval> intervals) {
    	
        List<Interval> result = new ArrayList<Interval>();
        
        if (intervals == null || intervals.isEmpty()) {
    		return result;
    	}
        
        Collections.sort(intervals, new Comparator<Interval>() {

			@Override
			public int compare(Interval a, Interval b) {
				return a.start - b.start;
			}
        });

        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        for (int i = 1; i < intervals.size(); i++) {
        	Interval interval = intervals.get(i);
        	if (interval.start > end) {
        		result.add(new Interval(start, end));
        		start = interval.start;
        		end = interval.end;
        	} else if (interval.start <= end && interval.end > end) {
        		end = interval.end;
        	}
        }
        
        result.add(new Interval(start, end));
        
        return result;
    }
}
