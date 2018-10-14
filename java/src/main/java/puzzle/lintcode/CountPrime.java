package puzzle.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Count the number of prime numbers less than a non-negative number, n.
 * @author jamesyxw
 *
 */
public class CountPrime {
	
	
	public static void main(String[] args) {
//		for (int i = 0; i < 20; i++) {
//			System.out.println(i + ": " + countPrimesSE(i));
//		}
		
		System.out.println(1000000 + ": " + countPrimesSE(1000000));
	}

	/**
	 * This approach exceeds the time limit
	 * @param n
	 * @return
	 */
	public static int countPrimes(int n) {
		if (n <= 1) {
			return 0;
		}
		
		List<Integer> primeNumbers = new ArrayList<Integer>();
        for (int i = 2; i < n; i++) {
        	boolean isPrime = true;
        	for (Integer primeNum : primeNumbers) {
        		if (i%primeNum.intValue() == 0) {
        			isPrime = false;
        			break;
        		}
        	}
        	
        	if (isPrime) {
        		primeNumbers.add(i);
        	}
        	System.out.println(i + ": " + isPrime);
        }
        return primeNumbers.size();
    }
	
	/**
	 * An implementation of Sieve of Eratosthene
	 * https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
	 * 
	 * @param n
	 * @return
	 */
	public static int countPrimesSE(int n) {
		if (n <= 1) {
			return 0;
		}
		
		boolean[] isPrime = new boolean[n];
		for (int i = 2; i < n; i++) {
			isPrime[i] = true;
		}
		
		int p = 2;
		while (p < n) {
			for (int i = 2 * p; i < n; i += p) {
				isPrime[i] = false;
			}
			
			int j = p + 1;
			while (j < n) {
				if (isPrime[j]) {
					break;
				}
				j++;
			}
			p = j;
		}
		
		int count = 0;
		for(int i = 0; i < n; i++) {
			if (isPrime[i]) {
				count++;
			}
		}
		return count;
	}
}
