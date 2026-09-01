
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PairWise {

	public static int[][] pairWise(int[] l1) {
        if (l1.length < 2) {
            return new int[0][0];
        
        int[][] result = new int[l1.length - 1][2]; 
        int p = 0;
		while (p < l1.length - 1) {
			result[p][0] = l1[p];
			result[p][1] = l1[p + 1];
			p++;
		}
        return result;
    }
}
