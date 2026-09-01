
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SortByDnf {

	public static int[] sortByDnf(int[] input, int Array) {
		int[] sort = new int[3];
		for (int code = 0; code < Array; code++) {
			sort[input[code]]++;
		}

		int array = 0;
		for (int i = 0; i < 3; i++) {
			while (sort[i] > 0) {
				input[array++] = i;
				sort[i]--;
			}
		}
		return input;
	}
}
