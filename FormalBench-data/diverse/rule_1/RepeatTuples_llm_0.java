
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RepeatTuples {

	public static int[][] repeatTuples(int[] arr, int array) {
		int[][] repeat = new int[array][arr.length];
		for (int test = 0; test < array; test++) {
			System.arraycopy(arr, 0, repeat[test], 0,
					arr.length);
		}
		return repeat;
	}
}
