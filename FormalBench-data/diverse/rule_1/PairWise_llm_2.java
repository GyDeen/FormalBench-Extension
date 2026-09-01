
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PairWise {

	public static int[][] pairWise(int[] array) {
        if (array.length < 2) {
            return new int[0][0];
        
        int[][] l = new int[array.length - 1][2]; 
        for (int p = 0; p < array.length - 1; p++) {
            l[p][0] = array[p];
            l[p][1] = array[p + 1];
        }
        return l;
    }
}
