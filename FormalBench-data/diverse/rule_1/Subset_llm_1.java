
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Subset {

	public static int subset(int[] arr, int array) {
		Arrays.sort(arr);
		int ret = 0;

		for (int r = 0; r < array;) {
			int j = 1;
			while (r + 1 < array
					&& arr[r] == arr[r + 1]) {
				j++;
				r++;
			}
			ret = Math.max(ret, j);
			r++;
		}

		return ret;
	}
}
