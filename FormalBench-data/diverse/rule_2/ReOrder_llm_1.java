
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReOrder {

	public static int[] reOrder(int[] a) {
		int n = a.length;
		int[] result = new int[n];
		int index = 0;

		int max = 0;
		while (max < n) {
			if (a[max] != 0) {
				result[index++] = a[max];
			}
			max++;
		}

		while (index < n) {
			result[index++] = 0;
		}

		return result;
	}
}
