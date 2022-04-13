public class Solution_2 {


    // Find sum of first 'n' numbers using Recursion
    // My approach - worked in first attempt, this is called 'Parameterized' approach
    static int getSum(int num, int sum) {

        if(num == 0) {
            return sum;
        }

        return getSum(num-1, sum+num);
    }


    // Functional Approach for 'sum of first 'n' numbers'
    static int getAns(int num) {

        if(num == 0) {
            return 0;
        }

        return num + getAns(num-1);
    }



    public static void main(String[] args) {

        System.out.println(getSum(5, 0));
        System.out.println(getAns(5));
    }
    
}
