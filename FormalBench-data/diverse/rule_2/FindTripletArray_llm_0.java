
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindTripletArray {

	public static int[] findTripletArray(int[] a, int arrSize, int sum) {
		if (arrSize < 3) {
			return new int[0];
		}

		int arr = 0;
		while (arr < arrSize - 2) {
			for (int j = arr + 1; j < arrSize - 1; j++) {
				for (int k = j + 1; k < arrSize; k++) {
					if (a[arr] + a[j] + a[k] == sum) {
						return new int[] { a[arr], a[j], a[k] };
					}
				}
			}
			arr++;
		}
		return new int[0];
	}
}
