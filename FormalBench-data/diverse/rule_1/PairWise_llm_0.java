
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PairWise {

	public static int[][] pairWise(int[] array) {
        if (array.length < 2) {
            return new int[0][0];
        
        int[][] l = new int[array.length - 1][2]; 
        for (int index = 0; index < array.length - 1; index++) {
            l[index][0] = array[index];
            l[index][1] = array[index + 1];
        }
        return l;
    }
}
