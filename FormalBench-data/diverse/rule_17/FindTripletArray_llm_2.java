
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindTripletArray {

	public static int[] findTripletArray(int[] a, int arrSize, int sum) {
		if (arrSize < 3) {
			return new int[0];
		}

		int arr = arrSize - 2;
		for (int i = 0; i < arr; i++) {
			int tmp = arrSize - 1;
			for (int j = i + 1; j < tmp; j++) {
				for (int k = j + 1; k < arrSize; k++) {
					int count = a[i] + a[j] + a[k];
					if (count == sum) {
						return new int[] { a[i], a[j], a[k] };
					}
				}
			}
		}
		return new int[0];
	}
}
