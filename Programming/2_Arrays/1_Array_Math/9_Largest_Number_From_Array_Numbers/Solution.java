import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;

import javax.sound.sampled.SourceDataLine;

public class Solution {

    public static String largestNumber(final List<Integer> A) {

        ArrayList<Integer> li = new ArrayList<>(A);
        // Collections.sort(li);

        System.out.println(li);
        for(int i = 0; i<li.size() - 1; i++) {

            for(int j = i + 1; j<li.size(); j++) {
                
                String n1 = li.get(i).toString();
                String n2 = li.get(j).toString();
                int a = Integer.parseInt(n1 + n2);
                int b = Integer.parseInt(n2 + n1);

                if(a < b) {

                    Collections.swap(li, i, j);
                }
            }
        }

        String ans = "";
        for(int i = 0; i<li.size(); i++) {
            ans += li.get(i).toString();
        }

        return ans;
    }

    public static void main(String[] args) {

        ArrayList<Integer> li = new ArrayList<Integer>(Arrays.asList(3, 34, 30, 9, 5));
        // System.out.println(largestNumber(li));

        String s = "9775076860243460";
        Long num = Long.parseLong(s);
        System.out.println(num);
    }
    
}
