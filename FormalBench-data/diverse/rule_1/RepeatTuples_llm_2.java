
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RepeatTuples {

	public static int[][] repeatTuples(int[] array, int Array) {
		int[][] repeat = new int[Array][array.length];
		for (int test = 0; test < Array; test++) {
			System.arraycopy(array, 0, repeat[test], 0,
					array.length);
		}
		return repeat;
	}
}
