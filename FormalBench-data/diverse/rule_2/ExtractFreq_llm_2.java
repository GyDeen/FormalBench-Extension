
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractFreq {

	public static int extractFreq(int[][] testArray) {
		HashSet<Integer> count = new HashSet<>();

		int total = 0;
		while (total < testArray.length) {
			int sum = 0;
			for (int j = 0; j < testArray[total].length; j++) {
				sum += testArray[total][j];
			}
			count.add(sum);
			total++;
		}
		return count.size();
	}
}
