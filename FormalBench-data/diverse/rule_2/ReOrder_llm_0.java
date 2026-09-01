
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReOrder {

	public static int[] reOrder(int[] a) {
		int n = a.length;
		int[] result = new int[n];
		int index = 0;

		int last = 0;
		while (last < n) {
			if (a[last] != 0) {
				result[index++] = a[last];
			}
			last++;
		}

		while (index < n) {
			result[index++] = 0;
		}

		return result;
	}
}
