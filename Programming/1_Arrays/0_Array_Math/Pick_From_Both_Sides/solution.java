import java.util.*;

class solution {
	
	/*
        * Compare first and last, pick 1
        * For example, if chosen last, then move last pointer to 1 left and pick from second last and first ...
        * Repeat this until 'B' elements are picked

        *** Above approach is wrong, because we need to pick in contigous manner.
        *** We cannot pick 1st then 3rd from one end.

        * Online Approach 1 (I thought of this, but could not think completely)
        ** Take sum of B elements from left, store it in max
        ** Then add last item and remove Bth item from front
        ** slowly do this till front end of array is almost over, keep max value updated
    */
	public static int solve(ArrayList<Integer> A, int B) {

        	int max_ans = 0;
        	int sum = 0;
       		int i = 0;
        	for(i = 0; i<B; i++) {
            	sum += A.get(i);
        	}

        	max_ans = sum;

        	int j = A.size() - 1;
        	while(B>0) {
            		sum += A.get(j--);
            		sum -= A.get(--i);
            		max_ans = Math.max(max_ans, sum);

        	}
		/*
		 * In above statement, sum += A.get(j--) and sum -= A.get(--i);
		 * This was a little tricky, had to see solution, because I was stuck on : sum += A.get(j--) and sum -= A.get(i--);
		 * Think deeply how these operators '--' work and with what logic are they used here
		 * Then this makes complete sense
		 * Need to inculcate this sense of thinking.
		 */
		return max_ans;
    	}

	public static void main(String args[]) {
	
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1,2,-1,5,8,0,-3,11));
		Integer B = 5;


		System.out.println(solve(list, B));
	}
}
