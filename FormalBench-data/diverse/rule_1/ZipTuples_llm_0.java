
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ZipTuples {

	public static int[][] zipTuples(int[] array, int[] test) {
		int arr = array.length;
		int[][] result = new int[arr][2];

		for (int num = 0; num < arr; num++) {
			result[num][0] = array[num];
			result[num][1] = test[num % test.length];
		}
		return result;
	}
}
