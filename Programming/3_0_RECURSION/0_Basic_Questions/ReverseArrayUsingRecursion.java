import java.util.Collections;

public class ReverseArrayUsingRecursion {




    static int[] reverseArray(int arr[], int i, int j) {

        if(j<=i) {
            return arr;
        }

       int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        return reverseArray(arr, i+1, j-1);

        
    }



    public static void main(String[] args) {

        int arr[] = {1,2,3,4,5};
        int i = 0;
        int j = arr.length - 1;

        int x[] = reverseArray(arr, i, j);

        for(int num: x) {
            System.out.println(num);
        }
    }
    
}
