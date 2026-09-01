
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PairWise {

	public static int[][] pairWise(int[] l1) {
        if (2 > l1.length) {
            return new int[0][0];
        
        int[][] result = new int[l1.length - 1][2]; 
        for (int i = 0; l1.length - 1 > i; i++) {
            result[i][0] = l1[i];
            result[i][1] = l1[i + 1];
        }
        return result;
    }
}
