
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PairWise {

	public static int[][] pairWise(int[] l1) {
        if (l1.length < 2) {
            return new int[0][0];
        
        int[][] result = new int[l1.length - 1][2]; 
        int tmp = 0;
		while (tmp < l1.length - 1) {
			result[tmp][0] = l1[tmp];
			result[tmp][1] = l1[tmp + 1];
			tmp++;
		}
        return result;
    }
}
