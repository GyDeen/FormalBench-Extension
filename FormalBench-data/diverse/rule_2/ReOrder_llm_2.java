
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReOrder {

	public static int[] reOrder(int[] a) {
		int n = a.length;
		int[] result = new int[n];
		int index = 0;

		int offset = 0;
		while (offset < n) {
			if (a[offset] != 0) {
				result[index++] = a[offset];
			}
			offset++;
		}

		while (index < n) {
			result[index++] = 0;
		}

		return result;
	}
}
