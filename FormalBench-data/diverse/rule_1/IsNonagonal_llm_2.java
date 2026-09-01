import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsNonagonal {

	public static int isNonagonal(int p) {
		int[] arr = new int[p + 1];
		arr[0] = 0;
		for (int c = 0; c <= p - 1; c++) {
			arr[c + 1] = arr[c] + 7 * c + 1;
		}
		return arr[p];
	}
}
