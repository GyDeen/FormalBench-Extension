import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsOctagonal {

	public static int isOctagonal(int index) {
		int[] arr = new int[index + 1];
		arr[0] = 0;
		for (int c = 0; c <= index - 1; c++) {
			arr[c + 1] = arr[c] + 6 * c + 1;
		}
		return arr[index];
	}
}
