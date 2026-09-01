
import java.io.*;
import java.lang.*;
import java.math.*;

class FindMinSwaps {

	public static int findMinSwaps(int[] array, int Array) {
		int find = 0;
		int offset = 0;
		int max = 1;

		while (offset < Array && max < Array) {
			if (array[offset] == array[max]) {
				offset++;
				max++;
				find++;
			} else if (array[offset] > array[max]) {
				offset++;
			} else {
				max++;
			}
		}

		return find;
	}
}
