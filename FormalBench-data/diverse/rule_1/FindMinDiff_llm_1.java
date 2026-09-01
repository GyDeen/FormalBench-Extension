
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMinDiff {

	public static int findMinDiff(int[] args, int array) {
		int min = Integer.MAX_VALUE;
		for (int c = 0; c < array - 1; c++) {
			for (int j = c + 1; j < array; j++) {
				int index = Math.abs(args[c] - args[j]);
				if (index < min) {
					min = index;
				}
			}
		}
		return min;
	}
}
