import java.util.List;

package 2_Count_Subsequences_with_sum_k;

/*
    Count the number of subsequneces which sum up to 'K'.
*/


public class Solution {
    

    /*
        Approach
            -> Approach is similar to print all subsequneces with target 'K'
            -> We could have used a global variable and incremented it, but the problem was not about that.
            -> So, we need to come up with an algorithm which does inside function.
                -> So, if each successful set which sums up to target can be counted as 1
                -> Then, each time we hit this case, we can return 1.
                    -> Else, we return 0 (subset does not hit condition)
                -> Now, we want all of these such cases
                    -> Simply, we need to get the sum of all cases of all recursive trees (choose / not choose element)
                -> So, in order to get for one recrusive tree, we will just return sum of it's children (choose/ not choose element)
                -> And finally we can have our answer.

    */

    public static int getCount(int nums[], int k, int sum, List<Integer> li, int index) {
        
        if(index > nums.length) {

            if(sum == k) {

                return 1;
            }
            else
                return 0;
        }

        sum+=nums[index];
        li.add(nums[index]);
        int l = getCount(nums, k, sum, li, index+1);

        sum-=nums[index];
        li.remove(li.size()-1);
        int r = getCount(nums, k, sum, li, index+1);;


        return l + r;

    }

    public static void main(String[] args) {
        
    }
}
