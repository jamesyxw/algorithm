package puzzle.lintcode.amazon;

import java.util.HashMap;
import java.util.Map;

/**
 * There is a one-dimensional board with a starting point on the far left side of the board 
 * and an end point on the far right side of the board. 
 * 
 * There are several positions on the board that are connected to other positions, 
 * ie if A is connected to B, then when chess falls at position A, 
 * you can choose whether to move the chess from A to B. And the connection is one way, 
 * which means that the chess cannot move from B to A. 
 * 
 * Now you have a six-sided dice, find the minimum steps to reach the end.
 * 
 * the index starts from 1.
	length > 1
	The starting point is not connected to any other location
	connections[i][0] < connections[i][1]

	input:
	length = 10
	connections = [[2, 10]]
	output:
	1

 * @author jamesyxw
 *
 */
public class ModernLudoI {
	
	public static void main(String[] args) {
		int length = 10;
		int[][] connections = new int[1][2];
		connections[0][0] = 2;
		connections[0][1] = 10;
		
		modernLudo(length, connections);
		
		int length2 = 15;
		int[][] connections2 = new int[2][2];
		connections2[0][0] = 7;
		connections2[0][1] = 9;
		connections2[1][0] = 8;
		connections2[1][1] = 14;
		modernLudo(length2, connections2);
		
		int length3 = 79;
		int[][] connections3 = {{5, 27}, {37, 70}, {16, 44}, {10, 22}, {17, 67}, {45, 50}};
		modernLudo(length3, connections3);
		
		int length4 = 73;
		int[][] connections4 = {{42,44},{8,18},{43,50},{7,58},{4,16},{39,55},{13,22},{47,49},{45,58},{38,60},{33,43}};
		modernLudo(length4, connections4);
		
		int length5 = 30;
		int[][] connections5 = {{2,9},{9,20},{20,30}};
		modernLudo(length5, connections5);
	}

	/**
     * @param length: the length of board
     * @param connections: the connections of the positions
     * @return: the minimum steps to reach the end
     */
    public static int modernLudo(int length, int[][] connections) {
    	if (length <= 7) {
    		return 1;
    	}
    	
    	//f[i] is the min steps to go from 1 to i, for example, f[10] is min step to go from 1 to 10
        int[] f = new int[length + 1];
        
        f[0] = -1;
        f[1] = 0;
        for (int i = 2; i <= length; i++) {
        	f[i] = Integer.MAX_VALUE; 
        }
        
        //Create a map of connections that use the start point as the key, and end point as the value
        Map<Integer, Integer> conns = new HashMap<Integer, Integer>();
        for (int i = 0; i < connections.length; i++) {
        	conns.put(connections[i][0], connections[i][1]);
        }

        for (int i = 2; i < length + 1; i++) {
        	if (i <= 7) {
        		f[i] = 1;
        	} else {
        		//Update the minPrevSix based on the current min
            	for (int j = i - 1; j >= i - 6; j--) {
            		f[i] = Math.min(f[i], f[j] + 1);
            	}
        	}
        	
        	if (conns.containsKey(i)) {
        		int endConn = conns.get(i);
        		f[endConn] = Math.min(f[endConn], f[i]);
        	}
        }
        
        for (int i = 0; i < length + 1; i++) {
        	if (f[i] < 10) {
        		System.out.print(f[i] + " , ");
        	} else {
        		System.out.print(f[i] + ", ");
        	}
        }
        System.out.println();
        System.out.print(" ");
        for (int i = 0; i < length + 1; i++) {
        	if (i < 10) {
        		System.out.print(i + " , ");
        	} else {
        		System.out.print(i + ", ");
        	}
        }
        System.out.println("\n");
        return f[length];
    }
    
}
