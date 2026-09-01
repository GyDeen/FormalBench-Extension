
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ZipTuples {

	public static int[][] zipTuples(int[] array, int[] test) {
		int arr = array.length;
		int[][] tmp = new int[arr][2];

		for (int num = 0; num < arr; num++) {
			tmp[num][0] = array[num];
			tmp[num][1] = test[num % test.length];
		}
		return tmp;
	}
}
