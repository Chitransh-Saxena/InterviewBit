/*
 * Non complicated case, current digit is not 9. Just add 1.
 * If the digit involves 9, two cases arise
 * 	* All digits are 9, then just add to the beginning of the array and rest all digits become 0
 * 	* Not all are 9, then just one digit increases, and 9s become ones.
 */

import java.util.*;
import java.lang.*;
class Solution {


	public static ArrayList<Integer> solve(ArrayList<Integer> li) {


		int carry = 0;
		boolean add = true;
		for(int i = li.size()-1; i>=0; i--) {
			
			int curr_num = li.get(i);

			if(add == true) {

				if(curr_num < 9) {

					li.remove(i);
					li.add(i, curr_num + 1);
					add = false;

					// return li;
				}
				if(i == 0 && curr_num == 0 && li.size() != 1) {
					li.remove(i);
					
					return li;
				}
				if(i == 0 && curr_num == 9) {
					
					li.remove(i);
					li.add(i, 0);
					li.add(0, 1);
					add = false;
					return li;
				}

				if(curr_num == 9) {
					
					li.remove(i);
					li.add(i, 0);
					
					carry = 1;
				}

			}

		}

		for(int i = 0; i<li.size(); i++) {
			
			if(li.get(i) != 0) {
				
				return li;
			}
			else {
				li.remove(i);
				i-=1
			}
		}

		return li;
	}

	public static void main(String[] args) {

		// List<Integer> li = Arrays.asList(0, 3, 7, 6, 4, 0, 5, 5, 6);
		
		List<Integer> li = Arrays.asList(0);
		ArrayList<Integer> arr = new ArrayList<Integer>(li);

		//		li.add(9);
//		li.add(9);
//		li.add(9);

		System.out.println(solve(arr));
	}
}
