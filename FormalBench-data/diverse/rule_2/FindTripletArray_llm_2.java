
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindTripletArray {

	public static int[] findTripletArray(int[] a, int arrSize, int sum) {
		if (arrSize < 3) {
			return new int[0];
		}

		int num = 0;
		while (num < arrSize - 2) {
			for (int j = num + 1; j < arrSize - 1; j++) {
				for (int k = j + 1; k < arrSize; k++) {
					if (a[num] + a[j] + a[k] == sum) {
						return new int[] { a[num], a[j], a[k] };
					}
				}
			}
			num++;
		}
		return new int[0];
	}
}
