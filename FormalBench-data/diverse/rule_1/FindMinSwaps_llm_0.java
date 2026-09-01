
import java.io.*;
import java.lang.*;
import java.math.*;

class FindMinSwaps {

	public static int findMinSwaps(int[] ints, int array) {
		int find = 0;
		int offset = 0;
		int max = 1;

		while (offset < array && max < array) {
			if (ints[offset] == ints[max]) {
				offset++;
				max++;
				find++;
			} else if (ints[offset] > ints[max]) {
				offset++;
			} else {
				max++;
			}
		}

		return find;
	}
}
