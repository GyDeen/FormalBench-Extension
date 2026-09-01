
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractFreq {

	public static int extractFreq(int[][] arr) {
		HashSet<Integer> result = new HashSet<>();

		for (int test = 0; test < arr.length; test++) {
			int index = 0;
			for (int n = 0; n < arr[test].length; n++) {
				index += arr[test][n];
			}
			result.add(index);
		}
		return result.size();
	}
}
