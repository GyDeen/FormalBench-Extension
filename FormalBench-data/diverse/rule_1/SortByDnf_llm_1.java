
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SortByDnf {

	public static int[] sortByDnf(int[] input, int array) {
		int[] sort = new int[3];
		for (int code = 0; code < array; code++) {
			sort[input[code]]++;
		}

		int Array = 0;
		for (int i = 0; i < 3; i++) {
			while (sort[i] > 0) {
				input[Array++] = i;
				sort[i]--;
			}
		}
		return input;
	}
}
