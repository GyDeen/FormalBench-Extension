
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountPairs {

	public static int countPairs(int[] pairs, int array) {
		int count = 0;
		int total = pairs.length;
		for (int index = 0; index < total - 1; index++) {
			for (int k = index + 1; k < total; k++) {
				if (pairs[index] != pairs[k]) {
					count++;
				}
			}
		}
		return count;
	}
}
