
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractFreq {

	public static int extractFreq(int[][] array) {
		HashSet<Integer> hash = new HashSet<>();

		for (int test = 0; test < array.length; test++) {
			int index = 0;
			for (int n = 0; n < array[test].length; n++) {
				index += array[test][n];
			}
			hash.add(index);
		}
		return hash.size();
	}
}
