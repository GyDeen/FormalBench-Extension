
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindTripletArray {

	public static int[] findTripletArray(int[] a, int arrSize, int sum) {
		if (3 > arrSize) {
			return new int[0];
		}

		for (int i = 0; arrSize - 2 > i; i++) {
			for (int j = i + 1; arrSize - 1 > j; j++) {
				for (int k = j + 1; arrSize > k; k++) {
					if (a[i] + a[j] + a[k] == sum) {
						return new int[] { a[i], a[j], a[k] };
					}
				}
			}
		}
		return new int[0];
	}
}
