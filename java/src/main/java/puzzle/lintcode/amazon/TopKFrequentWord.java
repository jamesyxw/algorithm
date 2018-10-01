package puzzle.lintcode.amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import puzzle.lintcode.common.ArrayUtils;

/**
 * Given a list of words and an integer k, return the top k frequent words in the list.

    You should order the words by the frequency of them in the return list, the most frequent one comes first. 
    If two words has the same frequency, the one with lower alphabetical order come first.
    
    Example
    Given
    
    [
        "yes", "lint", "code",
        "yes", "code", "baby",
        "you", "baby", "chrome",
        "safari", "lint", "code",
        "body", "lint", "code"
    ]
    for k = 3, return ["code", "lint", "baby"].
    
    for k = 4, return ["code", "lint", "baby", "yes"],
    
    Challenge
    Do it in O(nlogk) time and O(n) extra space.


 * @author jamesyxw
 *
 */
public class TopKFrequentWord {
    
    public class Word {
        String word;
        int freq;
        public Word(String word, int freq) {
            this.word = word;
            this.freq = freq;
        }
    }
    
    public static void main(String[] args) {
        String[] words = {"yes", "lint", "code",
                "yes", "code", "baby",
                "you", "baby", "chrome",
                "safari", "lint", "code",
                "body", "lint", "code"};
        TopKFrequentWord sol = new TopKFrequentWord();
        String[] result = sol.topKFrequentWords(words, 4);
        ArrayUtils.display(result);
        
        String[] words2 = {"aa", "ab"};
        TopKFrequentWord sol2 = new TopKFrequentWord();
        String[] result2 = sol.topKFrequentWords(words2, 1);
        ArrayUtils.display(result2);
    }
    
    /**
     * @param words: an array of string
     * @param k: An integer
     * @return: an array of string
     */
    public String[] topKFrequentWords(String[] words, int k) {
        if(words.length <= k) {
            return words;
        }
        
        String[] result = new String[k];
        Map<String, Integer> wordFreq = new LinkedHashMap<String, Integer>();
        
        for (int i = 0; i < words.length; i++) {
            if (wordFreq.containsKey(words[i])) {
                int freq = wordFreq.get(words[i]);
                wordFreq.put(words[i], freq + 1);
            } else {
                wordFreq.put(words[i], 1);
            }
        }
        
        List<Word> wordList = new ArrayList<Word>();
        
        for(Entry<String, Integer> entry : wordFreq.entrySet()) {
            wordList.add(new Word(entry.getKey(), entry.getValue()));
        }
        
        Collections.sort(wordList, new Comparator<Word>() {

            @Override
            public int compare(Word a, Word b) {
                if(a.freq == b.freq) {
                    return a.word.compareTo(b.word);
                }
                return b.freq - a.freq;
            }
        });
        
        int j = 0; 
        for (int i = 0; i < Math.min(wordList.size(), k); i++) {
            result[i] = wordList.get(i).word;
        }
        
        return result;
    }
}
