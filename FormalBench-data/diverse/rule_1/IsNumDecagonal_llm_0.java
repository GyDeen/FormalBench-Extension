import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsNumDecagonal {

	public static int isNumDecagonal(int num) {
		int[] arr = new int[num + 1];
		arr[0] = 0;
		for (int c = 0; c <= num - 1; c++) {
			arr[c + 1] = arr[c] + 8 * c + 1;
		}
		return arr[num];
	}
}
