package puzzle.lintcode.amazon;

import java.util.Arrays;

/**
 * Given two integers L and R, find the count of numbers in the range [L, R] (inclusive) 
 * having a prime number of set bits in their binary representation.

   (Recall that the number of set bits an integer has is the number of 1s present when written in binary. 
   For example, 21 written in binary is 10101 which has 3 set bits. Also, 1 is not a prime.)
   
   1.L, R will be integers L <= R in the range [1, 10^6].
   2.R - L will be at most 10000.
   
   Example 1:

    Input: L = 6, R = 10
    Output: 4
    Explanation:
    6 -> 110 (2 set bits, 2 is prime)
    7 -> 111 (3 set bits, 3 is prime)
    9 -> 1001 (2 set bits , 2 is prime)
    10->1010 (2 set bits , 2 is prime)
    Example 2:
    
    Input: L = 10, R = 15
    Output: 5
    Explanation:
    10 -> 1010 (2 set bits, 2 is prime)
    11 -> 1011 (3 set bits, 3 is prime)
    12 -> 1100 (2 set bits, 2 is prime)
    13 -> 1101 (3 set bits, 3 is prime)
    14 -> 1110 (3 set bits, 3 is prime)
    15 -> 1111 (4 set bits, 4 is not prime)

 * @author jamesyxw
 *
 */
public class PrimeNumberOfSetBitsInBinary {
    
    boolean[] isPrime = new boolean[31]; //signed interger has at most 31 bits
    
    public static void main(String[] args) {
        PrimeNumberOfSetBitsInBinary sol = new PrimeNumberOfSetBitsInBinary();
        System.out.println(sol.countPrimeSetBits(10,  15));
    }
    
    public PrimeNumberOfSetBitsInBinary() {
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i < isPrime.length; i++) {
            if (isPrime[i]) {
                for (int j = 2; j * i < isPrime.length;j++) {
                    isPrime[j*i] = false;
                }
            }
        }
    }
    
    /**
     * @param L: an integer
     * @param R: an integer
     * @return: the count of numbers in the range [L, R] having a prime number of set bits in their binary representation
     */
    public int countPrimeSetBits(int L, int R) {
        
        int count = 0;
        for (int i = L; i <= R; i++) {
            int bits = getSetBits(i);
            System.out.println(String.format("i=%s bits=%s", i, bits));
            if (isPrime(bits)) {
                count++;
            }
        }
        
        return count;
        
    }

    private boolean isPrime(int bits) {
        return isPrime[bits];
    }
    
    
    
    private int getSetBits(int i) {
        if (i < 1) {
            return 0;
        }
        
        int count = 0;
        while (i > 0) {
            if (i%2 == 1) {
                count++;
                i = (i - 1)/2;
            } else {
                i = i/2;
            }
        }
        return count;
    }

}
