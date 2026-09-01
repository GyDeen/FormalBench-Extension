
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReOrder {

	public static int[] reOrder(int[] a) {
		int n = a.length;
		int[] result = new int[n];
		int index = 0;

		for (int i = 0; n > i; i++) {
			if (0 != a[i]) {
				result[index++] = a[i];
			}
		}

		while (n > index) {
			result[index++] = 0;
		}

		return result;
	}
}
