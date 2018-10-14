package puzzle.lintcode;

/**
 * Given two strings representing two complex numbers.

    You need to return a string representing their multiplication. Note i^2 = -1 according to the definition.
    
    1.The input strings will not have extra blank.
    2.The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range of [-100, 100]. And the output should be also in this form.
    
    Example 1:
    Input: "1+1i", "1+1i"
    Output: "0+2i"
    Explanation: (1 + i) * (1 + i) = 1 + i^2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
   
    Example 2:
    Input: "1+-1i", "1+-1i"
    Output: "0+-2i"
    Explanation: (1 - i) * (1 - i) = 1 + i^2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
    
 * @author jamesyxw
 *
 */
public class ComplexNumberMultiplication {
    
    public static void main(String[] args) {
        ComplexNumberMultiplication sol = new ComplexNumberMultiplication();
        
        System.out.println(sol.complexNumberMultiply("1+1i", "1+1i"));
        System.out.println(sol.complexNumberMultiply("1+-1i", "1+-1i"));
    }

    /**
        (x + yi) * (m + ni) = (xm - yn) + (xn + ym)i

     * @param a: a string
     * @param b: a string
     * @return: a string representing their multiplication
     */
    public String complexNumberMultiply(String a, String b) {
        if(a == null || b == null || a.isEmpty() || b.isEmpty()) {
            return "";
        }
        
        int[] aDecomposed = getRealImaginary(a);
        int[] bDecomposed = getRealImaginary(b);

        StringBuilder sb = new StringBuilder();
        sb.append(int2str(aDecomposed[0] * bDecomposed[0] - aDecomposed[1] * bDecomposed[1]))
        .append("+")
        .append(int2str(aDecomposed[0] * bDecomposed[1] + aDecomposed[1] * bDecomposed[0]))
        .append("i");
        
        return sb.toString();
    }

    /**
        Convert a complex number in string representation to an array of size 2:
        0: real 
        1: imaginary
        Example: 2+3i, -2+3i, 2+-3i
     */
    private int[] getRealImaginary(String complexNum) {
        int[] res = new int[2];
        String[] parts = complexNum.split("[+]");
        if (parts[0].startsWith("-")) {
            res[0] = -Integer.valueOf(parts[0].substring(1));
        } else {
            res[0] = Integer.valueOf(parts[0]);
        }

        //strip off the i
        parts[1] = parts[1].substring(0, parts[1].length() - 1);
        if (parts[1].startsWith("-")) {
            res[1] = -Integer.valueOf(parts[1].substring(1));
        } else {
            res[1] = Integer.valueOf(parts[1]);
        }

        return res;
    }

    private String int2str(int num) {
        StringBuilder sb = new StringBuilder();
        if (num < 0) {
            sb.append("-");
        }

        sb.append(Math.abs(num));
        return sb.toString();
    }
}
