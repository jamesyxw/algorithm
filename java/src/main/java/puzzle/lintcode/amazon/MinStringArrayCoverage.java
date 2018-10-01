package puzzle.lintcode.amazon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import puzzle.lintcode.common.Tag;


/**
 * Given a string collection tag_list, an array of strings all_tags, 
 * find the smallest all_tags sub-array contains all the string in the tag_list, 
 * output the length of this sub-array. If there is no return -1.
 * 
 *  1 <= |tag_list|,|all_tags| <=10000
    All string length <= 50

 * Example
    Given tag_list = ["made","in","china"], all_tags = ["made", "a","b","c","in", "china","made","b","c","d"], return 3.
    
    Explanation:
    ["in", "china","made"] contains all the strings in tag_list,6 - 4 + 1 = 3.
    Given tag_list = ["a"], all_tags = ["b"], return -1.
    
    Explanation:
    Does not exist.

 * @author jamesyxw
 *
 */
public class MinStringArrayCoverage {

    public static void main(String[] args) {
        MinStringArrayCoverage sol = new MinStringArrayCoverage();
        String[] tagList = {"a", "b", "c"};
        String[] allTags = {"d", "e", "b", "a", "a", "a", "b", "b", "c"};
        
//        System.out.println(sol.getMinimumStringArray(tagList, allTags));
        System.out.println(sol.getMinimumStringArray2(tagList, allTags));
        
        String[] tagList2 = {"cawb","sl","ocmhj","w"};
        String[] allTags2 = {"cawb","ocmhj","ocmhj","sl","r","boz","rvi","scn","sl","ocmhj","w","vxeaw","cawb","w","ocmhj","ocmhj","igyef","ocmhj","w","ocmhj","pxd"};
//        System.out.println(sol.getMinimumStringArray(tagList2, allTags2));
        System.out.println(sol.getMinimumStringArray2(tagList2, allTags2));
    }
    
    
    /**
     * This method use a hashmap and two pointer i and j
     * hashmap: keep track the index where the tag last exists in the alltags, initialize all tags from tagList to -1
     * i: starting index of search range for the subarray
     * j: ending index of the search range for the subarray
     * count: keep track of the number of unique tags from i to j in all tags, these tags must also exist in the tagList
     * min: the min length of the subarray in allTags that covers tagList
     * 
     * 1. initialize i, j, count to 0, hashmap is initialized with entries whose key set to the tags in the tagList, and value set to -1
     * 2. keep incrementing j, 
     *  a. if allTags[j] not exist in tagList, skip
     *  b. if last index of allTags[j] is -1, it means the tag hasn't occur between i and j in allTags, thus increment the count
     *  c. update the last index of allTags[j] in the hashmap to j
     *  d. check if the count is equals to the tagList: if yes, then the subarray from i to j in allTags contains all the tags in the tagList, update the min
     *  e. continue to increment i until the count is less than the tagList length: 
     *      - if allTags[i] doesn't exist in the tagList, skip; 
     *      - if allTags[i] is the last occurrence, decrement count
     *  
     * @param tagList
     * @param allTags
     * @return
     */
    public int getMinimumStringArray2(String[] tagList, String[] allTags) {
        if (tagList.length == 0 || allTags.length == 0) {
            return -1;
        }

        //hashmap and two pointers
        

        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int k = 0; k < tagList.length; k++) {
            map.put(tagList[k], -1);
        }
        
        int i = 0, j = 0, count = 0, min = Integer.MAX_VALUE;
        for(j = 0; j < allTags.length; j++) {
//            System.out.println(String.format("i=%s j=%s", i, j));
            if(!map.containsKey(allTags[j])) {
                //skip the element that doesn't exist in the tagList
                continue;
            }

            if(map.get(allTags[j]) == -1) {
                //current tag at j not encountered yet from i to j, thus increment count
                count++;
            }

            //update the last occurrence of tag at j
            map.put(allTags[j], j);

            //check if the count is equal to the tagList
            if(count == tagList.length) {
                //update min if i to j covers all the tags
                min = Math.min(min, j - i + 1);

                //keep incrementing j until i to j doesn't covers all the tags in the tagList
                while (count == tagList.length) {
//                    System.out.println(String.format("   i=%s j=%s", i, j));
                    if (!map.containsKey(allTags[i])) {
                        i++;
                        continue;
                    }
                    
                    if (i == map.get(allTags[i])) {
                        map.put(allTags[i], -1);
                        min = Math.min(min, j - i + 1);
                        count--;
                    }
                    //increment i
                    i++;
                }
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    /**
     * @param tagList: The tag list.
     * @param allTags: All the tags.
     * @return: Return the answer
     */
    public int getMinimumStringArray(String[] tagList, String[] allTags) {
        // min heap with one pointer
        
        if (tagList.length == 0) {
            return 0;
        }
        
        //Create a set of tagList to look up if a tag belongs to tagList
        Set<String> tagListSet = new HashSet<String>();
        for(int i = 0; i < tagList.length; i++) {
            tagListSet.add(tagList[i]);
        }
        
        Comparator<Tag> comp = new Comparator<Tag>() {

            @Override
            public int compare(Tag a, Tag b) {
                return a.index - b.index;
            }
            
        };
        PriorityQueue<Tag> heap = new PriorityQueue<Tag>(tagList.length, comp);
        
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < allTags.length; i++) {
            if(!tagListSet.contains(allTags[i])) {
                //if there's an element that doesn't belong to tagList, empty the heap by recreating a new one
                continue;
            }
            
            //special method to handle the insert, the heap should contain only unique tag to track their last occurrence with
            //the one that occurs earlies on the top
            insertHeap(heap, allTags[i], i);
            
            if (heap.size() == tagList.length) {
                //found the subarray that contains all tags in tagList: from the tag with the smallest index, top of the heap to i 
                min = Math.min(min, i - heap.peek().index + 1);
            }
        }
        
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private void insertHeap(PriorityQueue<Tag> heap, String tagVal, int i) {
        List<Tag> buffer = new ArrayList<Tag>();
        boolean isFound = false;
        while(!heap.isEmpty()) {
            Tag tag = heap.poll();
            buffer.add(tag);
            if (tag.val.equals(tagVal)) {
                tag.index = i;
                isFound = true;
                break;
            }
        }
        if(!isFound) {
            buffer.add(new Tag(tagVal, i));
        }
        for(Tag tag : buffer) {
            heap.offer(tag);
        }
    }
}
