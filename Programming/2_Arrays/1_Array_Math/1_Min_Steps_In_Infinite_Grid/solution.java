/*
 *
 * Given an infinite grid, and some initial positions (x,y) and sequence of other cells that need to be covered in the given order
 * Task is to find minimum number of steps to travel all the cells.
 * Movement can be done in 8 directions, up, down, left, right, diagonals
 * 
 * Thought process:
 * We are given a list of pairs denoting the cells (x,y).
 * First cell is the starting position and we have to cover all the cells in the "sequential" manner.
 *
 * So, one approach could be -> Take two points, (x1, y1) and (x2, y2) from the list of indices. Find the minimum number of steps to reach from (x1, y1) to (x2, y2)
 * Store the above answer and the find similar answer from (x2, y2) to (x3, y3).
 *
 * Now, what should be the approach to reach from (x1, y1) to (x2, y2)
 * -> One brute force way could be to traverse in all possible ways and check which one yields the shortest answer
 * -> Then the optimized way of this could be to use recursion in this.
 * -> Shortest path is the diagonal distance in most of the cases (hypotenuse theoram)
 * -> Find the nearest point which can be reached diagoanlly, then move to the target point in horizontal and vertical direction   
 * -> based on trying on pen-paper, we can observe the following. If I need to go from (x1, y1) to (x2, y2) and (x1 > x2 && y1 > y2)
 * -> Keep subtracting x1 and y1 by 1, till either x1 or y1 equates to x2 or y2.
 * -> Whichever point is reached first, that is the nearest point from the target coordinate. Move vertical or horizontal distance of the remaining size.
 */

class point {

	int x, y;
	point(int a, int b) {
		x = a;
		y = b;
	}
}

public class solution {


	public static int solve(point arr[], int s) {
	
		int ans = 0;

		for(int i = 1; i<arr.length; i++) {
			
			ans += solveOptimized(arr[i-1], arr[i]);
		}

		return ans;
		
	}

	public static int solve(point a, point b) {
		
		int x1 = a.x;
		int y1 = a.y;

		int x2 = b.x;
		int y2 = b.y;
	
		int ans = 0;

		// Condition 1 - x1 > x2 and y1 > y2, this could be optimized to some mathematical formula
		if(x1 > x2 && y1 > y2) {
			
			while(x1 >= x2 || y1 >= y2) {
				x1-=1;
				y1-=1;

				ans +=1;
			}
			
			// We are on the correct y coordinate, need to move few x-axes distance
			if(x1 != x2) {
				ans = ans + (x1-x2);
			}
			else {
				ans = ans + (y1-y2);
			}
		}

		// Condition 2 - x1 < x2 && y1 < y2.
		else if(x1 < x2 && y1 < y2) {
			
			while(x1 <= x2 && y1 <= y2) {
				
				x1+=1;
				y1+=1;

				ans+=1;
			}

			if(x1 != x2) {
				ans = ans + (x2-x1);
			}
			else {
				ans = ans + (y2-y1);
			}
		}

		// Condition 3: x1 > x2 && y1 < y2
		else if(x1 > x2 && y1 < y2 ) {
			
			while(x1 >= x2 || y1 <= y2) {
				x1-=1;
				y1+=1;

				ans+=1;
			}

			if(x1 != x2) {
				ans = ans + (x1-x2);
			}
			else {
				ans = ans + (y2-y1);
			}
		}

		// Condition 4: x1 < x2 && y1 > y2;
		else if(x1 < x2 && y1 > y2) {
			
			while(x1 <= x2 && y1 >= y2) {
				x1+=1;
				y1-=1;

				ans+=1;
			}

			if(x1 != x2) {
				
				ans = ans + (x2-x1);
			}
			else {
				ans = ans + (y1-y2);
			}
		}

		System.out.println("Distance from " + a.x + "," + a.y + " to " + b.x + "," + b.y + " = " + ans);
		return ans;
	}

	public static int solveOptimized(point a, point b) {
		
		// Need to traverse the diagonal distance as much as possible
		int distance_x = Math.abs(a.x - b.x);
		int distance_y = Math.abs(a.y - b.y);

	
		// Based on pen-paper approach, whichever axes is closer, we will add those many minimum steps. Now, add vertical or horizontal distance pending to reach target.
		// This can be further optimized by understanding that, whichever is the maximum difference of axes, we would need to walk at least those many steps.	
		return Math.max(distance_x, distance_y);
	}
	
	public static void main(String args[]) {
		
		point arr[] = new point[4];
		arr[0] = new point(4,6);
		arr[1] = new point(1,2);
		arr[2] = new point(4,5);
		arr[3] = new point(10,12);

		int size = arr.length;
		System.out.println(solve(arr, size));

	}
}
