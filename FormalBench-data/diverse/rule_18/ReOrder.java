
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReOrder {

	public static int[] reOrder(int[] a) {
		int n = a.length;
		int[] result = new int[n];
		int index = 0;

		for (int i = 0; i < n; i++) {
			if (a[i] != 0) {
				result[index] = a[i];
				index++;
			}
		}

		while (index < n) {
			result[index] = 0;
			index++;
		}

		return result;
	}
}
