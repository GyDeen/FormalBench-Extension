
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountPairs {

	public static int countPairs(int[] pairs, int array) {
		int count = 0;
		int total = pairs.length;
		for (int index = 0; index < total - 1; index++) {
			for (int c = index + 1; c < total; c++) {
				if (pairs[index] != pairs[c]) {
					count++;
				}
			}
		}
		return count;
	}
}
