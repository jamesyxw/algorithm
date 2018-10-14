package puzzle.lintcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import puzzle.lintcode.common.ArrayUtils;
import puzzle.lintcode.common.TreeNode;

/**
 * Given the root of a tree, you are asked to find the most frequent subtree sum. 
 * The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). 
 * 
 * So what is the most frequent subtree sum value? 
 * If there is a tie, return all the values with the highest frequency in any order.
 * 
 * 
 * You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
 * 
 * 
    Examples 1
    Input:
    
      5
     /  \
    2   -3
    return [2, -3, 4], since all the values happen only once, return all of them in any order.
    
    Examples 2
    Input:
    
      5
     /  \
    2   -5
    return [2], since 2 happens twice, however -5 only occur once.
    
 * @author jamesyxw
 *
 */
public class MostFrequentSubtreeSum {
    
    public static void main(String[] args) {
        TreeNode a = new TreeNode(5);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(-5);
        
        a.left = b;
        a.right = c;
        
        MostFrequentSubtreeSum sol = new MostFrequentSubtreeSum();
        ArrayUtils.display(sol.findFrequentTreeSum(a));
        ArrayUtils.display(sol.findFrequentTreeSum(null));
    }
    
    int maxFreq = Integer.MIN_VALUE; 
    
    /**
     * @param root: the root
     * @return: all the values with the highest frequency in any order
     */
    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        //Store the mapping from sumtree sum to its frequency
        Map<Integer, Integer> sumFreq = new HashMap<Integer, Integer>();

        sumFrequency(sumFreq, root);
        
        List<Integer> result = new ArrayList<Integer>();
        for(Entry<Integer, Integer> entry : sumFreq.entrySet()) {
            if (entry.getValue() == maxFreq) {
                result.add(entry.getKey());
            }
        }
        
        int[] res = new int[result.size()];
        for(int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }

        return res;
    }

    /**
     * Post order search to find  the frequency of all the subtree sum
     * @param sumFreq
     * @param root
     * @return
     */
    public int sumFrequency(Map<Integer, Integer> sumFreq, TreeNode root) {
        if (root == null) {
            return 0;
        }

        int rootSum = root.val + sumFrequency(sumFreq, root.left) + sumFrequency(sumFreq, root.right);
        int freq = sumFreq.getOrDefault(rootSum, 0) + 1;
        sumFreq.put(rootSum, freq);
        maxFreq = Math.max(maxFreq, freq);
        
        return rootSum;
    }
    
}
