package puzzle.lintcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Give a List of data representing the coordinates[x,y] of each restaurant. 
 * Customer's coordinates are at the origin[0,0].Find out the n restaurants closest to the customer ,
 * you have to pick the restaurant that first appeared, although it may not be the best option. 
 * return their coordinates in the original order.
 * 
 *  1.Coordinates in range [-1000,1000]
    2.n>0
    3.No same coordinates
    
    Example
    
        Given : n = 2 , List = [[0,0],[1,1],[2,2]]
        Return : [[0,0],[1,1]]
        Given : n = 3,List = [[0,1],[1,2],[2,1],[1,0]]
        Return :[[0,1],[1,2],[2,1]]
 * 
 * @author jamesyxw
 *
 */
public class NumberOfRestaurants {
    
    public class ResturantInfo {
        public int distance = -1;
        public int index = -1;
        public ResturantInfo(int distance, int index) {
            this.distance = distance;
            this.index = index;
        }
    }
    /**
     * @param restaurant: 
     * @param n: 
     * @return: nothing
     */
    public List<List<Integer>> nearestRestaurant(List<List<Integer>> restaurant, int n) {
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        if (restaurant.size() == 0 || n > restaurant.size()) {
            return result;
        }
        
        Queue<ResturantInfo> heap = new PriorityQueue<ResturantInfo>(restaurant.size(), new Comparator<ResturantInfo>() {

            @Override
            public int compare(ResturantInfo a, ResturantInfo b) {
                if (a.distance == b.distance) {
                    return a.index - b.index;
                }
                
                return a.distance - b.distance;
            }
            
        });
        
        for (int i = 0; i < restaurant.size(); i++) {
            int distance = restaurant.get(i).get(0) * restaurant.get(i).get(0) + restaurant.get(i).get(1) * restaurant.get(i).get(1);
            heap.add(new ResturantInfo(distance, i));
        }
        
        List<ResturantInfo> topN = new ArrayList<ResturantInfo>();
        for (int i = 0; i < n; i++) {
            topN.add(heap.poll());
        }
        
        Collections.sort(topN, new Comparator<ResturantInfo>() {

            @Override
            public int compare(ResturantInfo a, ResturantInfo b) {
                return a.index - b.index;
            }
            
        });
        
        for (ResturantInfo current : topN) {
            result.add(restaurant.get(current.index));
        }
        
        return result;
    }
}
