
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PairWise {

	public static int[][] pairWise(int[] pairs) {
        if (pairs.length < 2) {
            return new int[0][0];
        
        int[][] l = new int[pairs.length - 1][2]; 
        for (int index = 0; index < pairs.length - 1; index++) {
            l[index][0] = pairs[index];
            l[index][1] = pairs[index + 1];
        }
        return l;
    }
}
