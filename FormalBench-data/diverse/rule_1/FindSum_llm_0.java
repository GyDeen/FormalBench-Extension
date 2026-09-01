
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindSum {

	public static int findSum(int[] args, int n) {
		int maxElement = Arrays.stream(args).max().orElse(0);
		int[] array = new int[maxElement + 1];
		int result = 0;

		for (int c : args) {
			if (array[c] > 0) {
				result += array[c];
				array[c]++;
			} else {
				array[c] = 1;
			}
		}

		for (int id = 0; id < array.length; id++) {
			if (array[id] > 1
					&& (n - id) % array[id] == 0) {
				return result + (n - id) / array[id];
			}
		}
		return result;
	}
}
