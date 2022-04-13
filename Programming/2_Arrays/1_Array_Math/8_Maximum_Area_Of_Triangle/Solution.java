/*
    -> Given a matrix of N rows and M columns with values being {'r', 'g' or 'b'}, we have to find maximum area of a triangle such that all 3 of it's vertices are different
    -> One more point here, One side of the triangle must be parallel to y-axis.

    Approach
        -> I could not think of any approach
        -> Here is Internet's approach
            -> Area of a triangle can be calculated either by heron's formula or (1/2 * base * Height)
            -> We are going to use second formula here, why?
                -> Because since we know one side is going to be parallel to y axis we can treat it as base.
            -> So, to maximise the area, we need to maximize Height or Base of the triangle
            -> Starting with base here
                -> Since we considered the side parallel to y-axis as base. we can try one approach here
                -> We can calculate two points on each column (every column is parallel to y axis, so every column has a potential 'base')
                    -> So, how to calculate the base?
                    -> In each column, find two points. These two points are basically two points of the base.
                    -> Find first and last occurence of RGB of each column. 
                        -> This way, we have two sets of 3 values for each column. 2 sets as in point 1 and point 2. 3 values as in occurence of 'r', 'g' and 'b'.
                -> And out of all these potential base, we can choose the maximum base.
            -> For maximum height
                -> Once we have the base, we need to to look for a third point, at the farthest of distance from the selected column.
                -> So, basically we can start looking from both sides, in each column, for the third color of edge.
                -> Since we are looking from the end, it is going to be the maximum value.
*/

public class Solution {


    // Mapping colors to some integral values.
    static int mapColor(char c)
    {
        if (c == 'r')
            return 0;
        else if (c == 'g')
            return 1;
        else if (c == 'b')
            return 2;


        System.out.println("Color = -1");
        return -1;
    }

    // Function to compute the area of 
    static double maxArea(char[][] mat, int R, int C) {

        int left[] = new int[3]; 
        int right[] = new int[3];

        int top[][] = new int[3][C];
        int bottom[][] = new int[3][C];

        /* 
            Top is a 2-D array which has 3 rows and C columns.
            3 rows because we have 3 colors, which we are mapping to some index. Check mapColor method
            The intent to fill top is to, fill it in such a way that we have first occurence of a color stored.

            For example. If we want first occurrence of 'r', and we have mapped it to index 0.
            We will store first occurence of 'r' at [0][1] for first column, [0][2] for second column.
            If we want first occurrence of color 'g', we will check the mapping. We have mapped 'g' to 1, so first occurrence will come 
            at [1][1]. Etc.

            For Bottom array,
            again similar logic applies, but this  time, we need the value which comes first when we see from the bottom.
        */

        for(int i = 0; i<3; i++) {
            for(int j = 0; j<C; j++) {

                // Since we want lower values in top array, we are initializing with integer max, and we want higher values in bottom array, we are initializing
                // With integer min.
                top[i][j] = Integer.MAX_VALUE;
                bottom[i][j] = Integer.MIN_VALUE;
            }
        }

        // Finding top and bottom points in each column.
        for(int j = 0; j<C; j++) {
            for(int i = 0; i<R; i++) {

                int x = mapColor(mat[i][j]);
                top[x][j] = Math.min(top[x][j], i);
                bottom[x][j] = Math.max(bottom[x][j], i);
            }
        }


        // Finding RGB cells for left and right (potential third vertex)
        // The logic here is, we need only column number and not the row number. Why is this?
        // Based on diagram of a triangle, once we have a base, we are looking for a third point. So, we should be looking for a point here (x,y) and not just 'y'
        // But, we are looking only for column here, if it does not matter if we get the element at 0,1 or 3,1 ..., what matters here is, that we have '1', and this one is at
        // some distance from the column where the base lies. Say, the base is 0,4 and 3,4. So, the height of this triangle is basically the distance between column 4 and column 1.
        // So, we don't really care about the row here, only column is what matters to determine the height of the triangle.

        
        // So, if we have potential third point at left, the column must be lower in value, so we initialize the left[] with integer max.
        // vice-versa logic for right[].
        // in left[3], we are basically storing the first column in which 'r','g' and 'b' are occuring.
        // So, in left[0], we will store the first column where red color is spotted
        // in left[1], we will store the first column where green color is spotted.

        for(int i = 0; i<3; i++) {
            left[i] = Integer.MAX_VALUE;
            right[i] = Integer.MIN_VALUE;
        }

        for(int i = 0; i<R; i++) {
            for(int j = 0; j<C; j++) {

                int x = mapColor(mat[i][j]);

                left[x] = Math.min(left[x], j);
                right[x] = Math.max(right[x], j);
            }
        }

        return findArea(mat, R, C, top, bottom, left, right);
    }

    public static double findArea(char[][] mat, int R, int C, int top[][], int bottom[][], int left[], int right[]) {

        double ans = (double)1;

        // For each column
        for(int j = 0; j<C; j++) {

            // For each top vertex of base. Loop is till 3 only because we have only 3 possible options of colors.
            // Since we are already looping for all the columns, we are basically looking for maximum possible areas 
            for(int x = 0; x<3; x++) {

                // For each bottom vertex of base
                for(int y = 0; y<3; y++) {

                    int z = 3 - x - y;      // This will denote the third color.

                    // Calculating max of left part based on current indices of the base.
                    if(x!=y && top[x][j] != Integer.MAX_VALUE && bottom[y][j] != Integer.MIN_VALUE && left[z] != Integer.MAX_VALUE) {

                        // Area = 1/2 * base * height
                        double expr = (((double)1/(double)2) * (bottom[y][j] - top[x][j]) * (j - left[z]));
                        ans = Math.max(ans, expr + 1);
                    }

                    // Calculating max area considering the third virtex is on right side.
                    if(x!=y && top[x][j] != Integer.MAX_VALUE && bottom[y][j] != Integer.MIN_VALUE && right[z] != Integer.MIN_VALUE) {

                        // Area = 1/2 * base * height
                        double expr = (((double)1/(double)2) * (bottom[y][j] - top[x][j]) * (right[z] -j));
                        ans = Math.max(ans, expr + 1);
                    }
                }
            }
        }

        return Math.ceil(ans);
    }
    
    public static void main(String[] args) {
        
        int R = 4;
        int C = 5;

        char mat[][] = {
            {'r', 'r', 'r', 'r', 'r'},
            {'r', 'r', 'r', 'r', 'g'},
            {'r', 'r', 'r', 'r', 'r'},
            {'b', 'b', 'b', 'b', 'b'}
        };

        System.out.println(maxArea(mat, R, C));
    }
}
