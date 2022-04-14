import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Solution {

    /*

        Question - Print all subsequences

        What is a subsequence
            -> Any sequence (contigous or non-contigous) which follows an order
            -> A sub-array is always contigous

        -> So, in order to generate all the subsequences, we need to basically select all the subsequences in some sort of sequential fashion
        -> Example [3, 1, 2]
            -> Then, all the subsequences will look like this
                -> [], [3,1], [1,2], [3,2], [3, 1, 2]
                -> How did I generate above subsets?
                    -> Well, sequentially pick elements from indices.
                    -> So, it has to go in a boolean manner where you select an index or NOT select an index.
                -> Now, in terms of code, we have to come up with an algorithm where we are able to select items like above.

        -> Algorithm
            -> We can try a recursive solution where we start with an empty list [] and index 0.
            -> From here on, we decide, what do we do with an element on that index
                -> We can add that element to the list
                -> We can not add element to that list
            -> No matter what we do (from above 2 options)
                -> We can proceed to do that same thing again (recursion), with next index (index + 1)
            -> This will be classic example of multiple recursion, but with a slight modification
            -> We definitely need to come up with a base case (either add element to some list or print it)

        
        -> Time complexity for below code?
            -> At each level, we are calling the recursive function 2 times.
                -> So, the recursion tree will grow exponentially
                -> Therefore, the number of recursion calls will be 2^n
            -> Time complexity of each function (apart from recursive calls)
                -> Addition to list takes O(1) time {It can become O(N) if we are copying the list}
                -> Removal from list takes O(N) time
                    -> This is because we have to iterate through the array to find the element and remove it.

            -> Therefore, the total time complexity for this method is O(2^n)*O(N)

    */

    public static void generateSubsets(int index, List<Integer> li, List<Integer> nums) {


        // Base case, if we reach the end of list, we just print the items added to our list of subsets.
        if(index >= nums.size()) {

            System.out.println(li);
            return;
        }
        
        // Add an element to the subset list, and move to the next index. This is "CHOOSE ELEMENT" condition
        li.add(nums.get(index));
        generateSubsets(index+1, li, nums);


        // We remove the element from the list (bring it back to original state), and move to next index. This is "NOT CHOOSE ELEMENT" condition
        li.remove(li.size()-1); 
        generateSubsets(index+1, li, nums);

    }
    
    public static void main(String[] args) {
        
        ArrayList<Integer> nums = new ArrayList<Integer>(Arrays.asList(3, 1 ,2));
        generateSubsets(0, new ArrayList<Integer>(), nums);

    }
}
