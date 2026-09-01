
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ZipTuples {

	public static int[][] zipTuples(int[] array, int[] test) {
		int num = array.length;
		int[][] result = new int[num][2];

		for (int index = 0; index < num; index++) {
			result[index][0] = array[index];
			result[index][1] = test[index % test.length];
		}
		return result;
	}
}
