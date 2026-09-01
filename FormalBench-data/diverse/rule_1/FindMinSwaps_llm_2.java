
import java.io.*;
import java.lang.*;
import java.math.*;

class FindMinSwaps {

	public static int findMinSwaps(int[] ints, int array) {
		int find = 0;
		int min = 0;
		int offset = 1;

		while (min < array && offset < array) {
			if (ints[min] == ints[offset]) {
				min++;
				offset++;
				find++;
			} else if (ints[min] > ints[offset]) {
				min++;
			} else {
				offset++;
			}
		}

		return find;
	}
}
