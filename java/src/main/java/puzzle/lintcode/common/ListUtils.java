package puzzle.lintcode.common;

import java.util.List;

public class ListUtils {
    
    public static void displayListList(List<List<Integer>> list) {
        for (List<Integer> tuple : list) {
            System.out.print("{");
            for (int i = 0; i < tuple.size(); i++) {
                System.out.print(tuple.get(i));
                if (i != tuple.size() -1 ) {
                    System.out.print(",");
                }
            }
            System.out.println("}");
        }
    }
}
