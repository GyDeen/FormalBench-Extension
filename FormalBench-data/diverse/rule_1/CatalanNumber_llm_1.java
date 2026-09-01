import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CatalanNumber {

	public static int catalanNumber(int c) {
		int[] arr = new int[c + 1];
		arr[0] = 1;
		for (int cat = 1; cat <= c; cat++) {
			arr[cat] = 0;
			for (int n = 0; n < cat; n++) {
				arr[cat] += arr[n]
						* arr[cat - n - 1];
			}
		}
		return arr[c];
	}
}
