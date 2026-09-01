import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CatalanNumber {

	public static int catalanNumber(int number) {
		int[] arr = new int[number + 1];
		arr[0] = 1;
		for (int c = 1; c <= number; c++) {
			arr[c] = 0;
			for (int n = 0; n < c; n++) {
				arr[c] += arr[n]
						* arr[c - n - 1];
			}
		}
		return arr[number];
	}
}
