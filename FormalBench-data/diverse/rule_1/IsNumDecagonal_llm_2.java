import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsNumDecagonal {

	public static int isNumDecagonal(int number) {
		int[] arr = new int[number + 1];
		arr[0] = 0;
		for (int c = 0; c <= number - 1; c++) {
			arr[c + 1] = arr[c] + 8 * c + 1;
		}
		return arr[number];
	}
}
