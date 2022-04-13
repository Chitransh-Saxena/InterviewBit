import java.util.*;

/* 
 * Given an array A, find the maximum value of |A[i] - A[j]| + |i-j|
 * Above could be solved using 2 for loops, saving value of each possible expression and returning the max of it.
 * Optimized approach from internet
 * 	* If we open the modulus sign, we get two possible expressions, negative and positive based on if the value is greater or less than 0
 * 	* So, for above expression, we have the following conditions and expressions
 * 	* If A[i] > A[j] and i > j, expr: (A[i] - A[j]) + (i-j) => (A[i] + i) - (A[j] + j)
 * 	* If A[i] < A[j] and i > j, expr: -(A[i] - A[j]) + (i-j) => (i-A[i]) - (j-A[j])
 * 	* If A[i] > A[j] and i < j, expr: (A[i] - A[j]) - (i-j) => (A[i] - i) - (:w
 *
	
*/



public class Solution {


	public static void main(String[] args) {
	
		
	}
}
