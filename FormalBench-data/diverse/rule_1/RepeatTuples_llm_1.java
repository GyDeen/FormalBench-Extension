
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RepeatTuples {

	public static int[][] repeatTuples(int[] arr, int array) {
		int[][] test = new int[array][arr.length];
		for (int index = 0; index < array; index++) {
			System.arraycopy(arr, 0, test[index], 0,
					arr.length);
		}
		return test;
	}
}
