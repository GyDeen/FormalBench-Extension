
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RightInsertion {

	public static int rightInsertion(int[] arr, int array) {
		int ret = 0;
		int n = arr.length - 1;
		while (ret <= n) {
			int k = (ret + n) / 2;
			if (arr[k] == array) {
				return k;
			} else if (arr[k] < array) {
				ret = k + 1;
			} else {
				n = k - 1;
			}
		}
		return ret;
	}
}
