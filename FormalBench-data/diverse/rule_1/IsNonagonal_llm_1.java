import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsNonagonal {

	public static int isNonagonal(int num) {
		int[] arr = new int[num + 1];
		arr[0] = 0;
		for (int p = 0; p <= num - 1; p++) {
			arr[p + 1] = arr[p] + 7 * p + 1;
		}
		return arr[num];
	}
}
