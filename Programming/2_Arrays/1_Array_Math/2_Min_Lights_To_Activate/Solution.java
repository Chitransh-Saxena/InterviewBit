/*
    -> Given an array of size N denoting bulbs in a corridor.
    -> The ith index is 0 if the bulb at this position is faulty, else it is 1
    -> All the bulbs are of specific power. A bulb of power 'b' placed at 'i' can light up from 'i - b + 1' to 'i + b -1'.
    -> Initially all bulbs are off
    -> Return min number of bulbs to be turned on to light up the whole corridor. Else -1. 


    Approach (Brute force)
        -> Traverse from the start and try to light up the current bulb
        -> So, if current bulb needs to be lit up, there is a range of bulbs out of which one has to be chosen.
            -> How to choose this bulb?
        -> So, if we are traversing the array from the left, our aim is to light up the are from left to right.
        -> So, we pick current bulb, and we think, which bulb to light up, so that maximum benefit can be taken out
            -> Use pen-paper approach here
        -> So, if we are traversing left to right, it is benefecial to light up the right most bulb which is eligible (in range and value 1).
            -> This way, current bulb is lit up, but also equal area to the right is also light up
            -> Benefit we get from this?
                -> When we have to move on, we directly move on to the next index aftet the recently lit up range.


    Approach (Finally used)
        -> Above approach was based on finding faulty bulbs. So it was not working properly
        -> In case, all the bulbs were working fine, then it was a dead end code.
        -> To improvise, above logic is retained, but in instance of current iteration, working bulb is being searched from the legit right end (i + b - 1), till left end (i - b + 1).
            -> If it is found, then we move on to next position (i = bulbFoundAt + B)
            -> Else we return -1
    

*/


public class Solution {

    public static int searchInRange(int[] arr, int l, int r) {

        if(r < arr.length) {

            if(l < 0) {
                l = 0;
            }
            for(int i = r; i!=l; i--) {
                if(arr[i] == 1) {
                    return i;
                }
            }
            return -1;
        }
        return -1;
        
    }
    
    public static int solve(int[] arr, int b) {

        int count = 0;
        for(int i = 0; i<arr.length; i++) {

            if(arr[i] == 0) {
                int bulb_to_light_up = searchInRange(arr, i - b + 1 , i + b - 1);
                if(bulb_to_light_up == -1) {
                    return -1;
                }
                else {
                    i = bulb_to_light_up +  b;
                }
                count +=1;
            }
        }

        if(count != 0) {
            return count;
        }
        else {
            return -1;
        }

    }

    public static int solve2(int[] arr, int b) {

        int count = 0;
        boolean bulb_found = false;

        // for(int i = 0; i<arr.length; i++) {

        //     int check_from = i + b - 1;
        //     int p = check_from;
        //     bulb_found = false;
        //     int lower_bound = i - b + 1;
        //     if(lower_bound < 0) {
        //         lower_bound = 0;
        //     }
        //     if(check_from >= arr.length) {
        //         check_from = arr.length - 1;
        //     }
        //     while(check_from > lower_bound || bulb_found == true) {
        //         if(arr[check_from] == 1) {
        //             bulb_found = true;
        //             break;
        //         }
        //         check_from -=1;
        //     }
            
        //     if(bulb_found == false) {
        //         return -1;
        //     }
        //     count+=1;
            
        //     i = check_from + b;
        // }

        for(int i = 0; i<arr.length; i++) {
            int j = i + b - 1;
            bulb_found = false;
            int lower_bound = i - b + 1;
            

            if(j >= arr.length) {
                j = arr.length - 1;
            }

            if(lower_bound < 0) {
                lower_bound = 0;
            }
            

            while(j >= lower_bound || bulb_found == true) {
                if(arr[j] == 1) {
                    bulb_found = true;
                    count+=1;
                    i = j + b;
                    break;
                }
                j--;
            }

            if(bulb_found == false) {
                return -1;
            }
        }


        return count;
    }


    public static void main(String[] args) {

        // int[] arr = {0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0};
        int[] arr = {1,1,1};
        // int[] arr = {1, 1, 0, 0, 1, 1};
        // int[] arr = {1, 0, 1, 0, 0, 0};
        System.out.println(solve2(arr,6));
    }
}
