
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindTripletArray {

	public static int[] findTripletArray(int[] a, int arrSize, int sum) {
		if (arrSize < 3) {
			return new int[0];
		}

		for (int i = 0; i < arrSize - 2; i++) {
			for (int j = i + 1; j < arrSize - 1; j++) {
				for (int k = j + 1; k < arrSize; k++) {
					if (sum == a[i] + a[j] + a[k]) {
						return new int[] { a[i], a[j], a[k] };
					}
				}
			}
		}
		return new int[0];
	}
}
