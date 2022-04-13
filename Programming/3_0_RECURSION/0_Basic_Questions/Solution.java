public class Solution {
    
    static void printNum(int num, int count) {

        if(count >= 5) {
            return;
        }

        System.out.println(num);
        count+=1;
        printNum(num, count);
    }


    static void printReverse(int num) {

        if(num == 0) {
            return;
        }

        System.out.println(num);
        num-=1;

        printReverse(num);
    }



    public static void main(String[] args) {
        
        // printNum(10, 0);
        printReverse(10);
    }
}
