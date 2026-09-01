
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractFreq {

	public static int extractFreq(int[][] testArray) {
		HashSet<Integer> count = new HashSet<>();

		int result = 0;
		while (result < testArray.length) {
			int sum = 0;
			for (int j = 0; j < testArray[result].length; j++) {
				sum += testArray[result][j];
			}
			count.add(sum);
			result++;
		}
		return count.size();
	}
}
