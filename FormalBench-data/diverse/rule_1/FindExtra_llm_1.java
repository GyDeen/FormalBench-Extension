
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindExtra {

	public static int findExtra(int[] args, int[] arr, int array) {
		int ret = 0;
		int r = 0;
		while (ret < args.length && r < arr.length) {
			if (args[ret] < arr[r]) {
				ret++;
			} else if (args[ret] > arr[r]) {
				r++;
			} else {
				ret++;
				r++;
			}
		}
		return ret < args.length ? ret : r;
	}
}
