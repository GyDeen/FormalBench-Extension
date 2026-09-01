
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindExtra {

	public static int findExtra(int[] args, int[] arr, int array) {
		int ret = 0;
		int len = 0;
		while (ret < args.length && len < arr.length) {
			if (args[ret] < arr[len]) {
				ret++;
			} else if (args[ret] > arr[len]) {
				len++;
			} else {
				ret++;
				len++;
			}
		}
		return ret < args.length ? ret : len;
	}
}
