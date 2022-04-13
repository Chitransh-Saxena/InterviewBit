public class MultipleRecursion {
    
    // Find fibonacci number
    // 0 1 1 2 3 5 8 13 21 ...

    public static int getFib(int n) {

        if(n == 0) {
            return 0;
        }

        if(n == 1) {
            return 1;
        }

        return getFib(n-1) + getFib(n-2);
    }


    public static void main(String[] args) {
        
        System.out.println(getFib(2));
    }
}
