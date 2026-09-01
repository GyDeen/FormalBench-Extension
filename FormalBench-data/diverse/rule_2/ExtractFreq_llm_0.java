
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractFreq {

	public static int extractFreq(int[][] testArray) {
		HashSet<Integer> count = new HashSet<>();

		int index = 0;
		while (index < testArray.length) {
			int sum = 0;
			for (int j = 0; j < testArray[index].length; j++) {
				sum += testArray[index][j];
			}
			count.add(sum);
			index++;
		}
		return count.size();
	}
}
