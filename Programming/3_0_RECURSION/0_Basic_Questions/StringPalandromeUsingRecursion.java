public class StringPalandromeUsingRecursion {
    

    /*
        This was my original approach.
        This could be further optimzied, but that is too much work to note down.
    */
    static boolean isPalandrome(String s, int i, int j) {

        if(j <= i) {
            return s.charAt(i) == s.charAt(j);
        }

        if(s.charAt(i) != s.charAt(j)) {
            return false;
        }

        return isPalandrome(s, i+1, j-1);
    }

    public static void main(String[] args) {


        String s = "aaccbaa";
        int i = 0, j = s.length() - 1;


        System.out.println(isPalandrome(s, i, j));
    }
}
