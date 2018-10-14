package alg.permcomb;

import java.util.ArrayList;
import java.util.List;

public class LexicographicPermutation {
    public static void main(String[] args) {
        String test = "abcd";
        List<String> combinations = permutation(test);
        
        for(String s : combinations) {
            System.out.println(s);
        }
    }
    
    public static List<String> permutation(String s) {
        List<String> combinations = new ArrayList<String>();
        permutate("", s, combinations);
        return combinations;
    }
    
    private static void permutate(String prefix, String s, List<String> combinations) {
        int n = s.length();
        if (n == 1) {
            combinations.add(prefix + s);
        } else {
            for (int i = 0; i < n; i++) {
                permutate(prefix + s.charAt(i), s.substring(0, i) + s.substring(i+1, n), combinations);
            }
        }
    }
}
