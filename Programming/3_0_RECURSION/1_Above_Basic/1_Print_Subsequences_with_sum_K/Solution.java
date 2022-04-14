// package 1_Print_Subsequences_with_sum_K;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static int count = 0;
    
    public static void helper(int[] nums, int index, int target, List<Integer> li, int sum) {
        
        if(index == nums.length) {
            
            if(sum == target) {
                System.out.println(li);
            }
            return;
        }
        
        
        li.add(nums[index]);
        sum+=nums[index];
        helper(nums, index+1, target, li, sum);
        
        li.remove(li.size()-1);
        sum-=nums[index];
        helper(nums, index+1, target, li, sum);
        
    }
    
    
    public static int numSubseq(int[] nums, int target) {
        
        helper(nums, 0, target, new ArrayList<Integer>(), 0);
        return count;
    }
    
  
    
    public static void main(String[] args) {

        int[] nums = {1,2,1};
        numSubseq(nums, 2);
        
    }
}
