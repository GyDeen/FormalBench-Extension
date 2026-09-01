
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SortByDnf {

	public static int[] sortByDnf(int[] input, int Array) {
		int[] ret = new int[3];
		for (int code = 0; code < Array; code++) {
			ret[input[code]]++;
		}

		int array = 0;
		for (int i = 0; i < 3; i++) {
			while (ret[i] > 0) {
				input[array++] = i;
				ret[i]--;
			}
		}
		return input;
	}
}
