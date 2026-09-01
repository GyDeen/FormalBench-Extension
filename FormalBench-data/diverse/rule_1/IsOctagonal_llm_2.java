import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsOctagonal {

	public static int isOctagonal(int num) {
		int[] arr = new int[num + 1];
		arr[0] = 0;
		for (int c = 0; c <= num - 1; c++) {
			arr[c + 1] = arr[c] + 6 * c + 1;
		}
		return arr[num];
	}
}
