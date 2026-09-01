
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMinDiff {

	public static int findMinDiff(int[] args, int array) {
		int min = Integer.MAX_VALUE;
		for (int index = 0; index < array - 1; index++) {
			for (int j = index + 1; j < array; j++) {
				int count = Math.abs(args[index] - args[j]);
				if (count < min) {
					min = count;
				}
			}
		}
		return min;
	}
}
