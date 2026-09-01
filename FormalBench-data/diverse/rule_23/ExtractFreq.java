
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractFreq {

	public static int extractFreq(int[][] testArray) {
		HashSet<Integer> count = new HashSet<>();

		for (int i = 0; testArray.length > i; i++) {
			int sum = 0;
			for (int j = 0; testArray[i].length > j; j++) {
				sum += testArray[i][j];
			}
			count.add(sum);
		}
		return count.size();
	}
}
