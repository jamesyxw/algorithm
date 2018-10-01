package puzzle.lintcode.common;

import java.util.List;

public class ListUtils<T> {
    
    public void displayListList(List<List<T>> list) {
        for (List<T> tuple : list) {
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
    
    public void displayList(List<T> list) {
        for(T num : list) {
            System.out.print(num + ",");
        }
        System.out.println("");
    }
}
