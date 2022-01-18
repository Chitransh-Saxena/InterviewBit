import java.util.*;

public class Solution {


    /*
        Given a list of numbers, find the maximum triplet sum such as a[i] + a[j] + a[k] = max_sum where 0<=i<j<k<=list.size()-1
        If no such sequence exists, return -1.


        Approach
            -> Brute Force
                -> Find all possible sums, find the max
                -> Basically, find max sum of 2 numbers.
                -> Follow same pattern to find third number
                -> It will be O(N^3)
        
        Approach
            -> Optimized
                -> We need to reduce this search which we are doing by iterating the entire list
                -> So, one way is to find the max sum of 2 numbers by doing a 2 poitner approach (Hoping this list is sorted)
                    -> Array is not sorted, this approach fails.
            
            -> Optimzed (Taken help from youtube and GFG)
                -> Start from index = 1 and pick this element as potential mid element
                    -> Now, 2 values have to be searched for.   
                    -> One, in the left sub-array with given constraints
                    -> One, on the right sub-array with given constraints
                -> So, this way, in one for loop
                    -> there is only one level of nesting (for loop for potential mid element, and inside this 2 for loops, one for left and one for right)
                    -> Unlike the original approach, where there was 3 levels of nesting (O(N^3))
    */

    public static int solve(List<Integer> li) {

        int ans = 0;
        boolean found = false;

        for(int i = 1; i<li.size(); i++) {

            int max_sum = 0;
            int l_max = 0, r_max = 0;
            for(int j = 0; j<i; j++) {
                if(li.get(j) < li.get(i)) {
                    l_max = Math.max(l_max, li.get(j));
                }
            }

            for(int j = i+1; j<li.size(); j++) {

                if(li.get(i) < li.get(j)) {
                    r_max = Math.max(r_max, li.get(j));
                }
            }
            
            found = true;
            max_sum = l_max + li.get(i) + r_max;
            ans = Math.max(ans, max_sum);
            
        }

        if(found != true) {
            return -1;
        }
        return ans;
        
    }
/*
 * Above approach also gives TLE for a large dataset
 * So, even more optimized approach is needed
 *
 * So, this approach is based on maximum suffix-array and binary search
 *
 * For finding a maximum number, greater than given number which is positioned after it, we can do the following
 * 	-> Maintain a maximum suffix array such that for any number (suffix[i]), it would contain maximum number from index i+1 .... n-1.
 * 	-> Calculating this suffix array would take O(N) time and O(N) space.
 *
 *
 * For finding maximum number smaller than the given number which is positioned before current item (<i), we can do the following
 * 	-> Maintain a sorted list of numbers before a given number such that we can simply perform a binary search.
 * 	-> This way, we can find a number just smaller than current number
 *
 *
 * 	Questions here
 * 	-> Why did we not build a suffixArray for left side too?
 * 		-> Example: 2 5 3 6 1 4 1
 * 		-> If I pick 3 as my element, so max left element is 5 here, so suffixArray would return 5
 * 		-> But based on condition (i<j and A.get(i) < A.get(j)), our left item should be 2.
 * 	->  How is the Binary Search operation approach cheaper?
 * 		-> Inserting the items into a set would cost O(LogN)
 * 		-> Getting the value just lower than the given value can be done by using lower() method
 * 		-> It applies binary search so it is O(LogN) too
 *
 *
 * 		Questions here
 * 		-> Why did we not build a suffixArray for left side too?
 * 			-> Example: 2 5 3 6 1 4 1
 * 			-> If I pick 3 as my element, so max left element is 5 here, so suffixArray would return 5
 * 			-> But based on condition (i<j and A.get(i) < A.get(j)), our left item should be 2.
 * 		->  How is the Binary Search operation approach cheaper?
 * 			-> Inserting the items into a set would cost O(LogN)
 * 			-> Getting the value just lower than the given value can be done by using lower() methond
 * 			-> It applies binary search so it is O(LogN) too..
 */

    public static Integer solveOptimized(List<Integer> li) {


	int ans = 0;

	// Create a maximum suffix array.
	int arr[] = new int[li.size()+1];

	// Set the last element as 0, because this would be considered when we populate the array from right end.
	arr[li.size()] = 0;

	// Populate the suffix-array
	for(int i = li.size() - 1; i>=0; i--) {
		
		// So, for current position, maximum number is comparison between existing right-most element or li.get(i)
		arr[i] = Math.max(arr[i+1], li.get(i));
	}


	// Initialize a set container, to maintain a sorted list of numbers to get left item.
	TreeSet<Integer> lowVal = new TreeSet<Integer>();

	
	// Minimum value is put as first value to make a legit first comparison.
	// If A.get(0) is added, we encouter an exception, it returns null, blocking all further program
	lowVal.add(Integer.MIN_VALUE);
	

	for(int i = 0; i<li.size()-1; i++) {
		
		if(arr[i+1] > li.get(i)) {

			ans = Math.max(ans, lowVal.lower(A.get(i)) + li.get(i) + arr[i+1]);
			

			// Insert current element into the lowVal set for further processing.
			// This ensures all elements from 0 - i-1 are present for comparison
			lowVal.add(li.get(i));
		}
	}

	return ans;


    }
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(2,5,3,1,4,9);
        System.out.println("Max Triplet Sum = " + solve(list));
    }
}
