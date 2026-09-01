
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PairWise {

	public static int[][] pairWise(int[] l1) {
        if (l1.length < 2) {
            return new int[0][0];
        
        int[][] result = new int[l1.length - 1][2]; 
        int p = l1.length - 1;
		for (int i = 0; i < p; i++) {
            result[i][0] = l1[i];
            result[i][1] = l1[i + 1];
        }
        return result;
    }
}
