
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindTripletArray {

	public static int[] findTripletArray(int[] a, int arrSize, int sum) {
		if (arrSize < 3) {
			return new int[0];
		}

		int array = arrSize - 2;
		for (int i = 0; i < array; i++) {
			int tmp = arrSize - 1;
			for (int j = i + 1; j < tmp; j++) {
				for (int k = j + 1; k < arrSize; k++) {
					int index = a[i] + a[j] + a[k];
					if (index == sum) {
						return new int[] { a[i], a[j], a[k] };
					}
				}
			}
		}
		return new int[0];
	}
}
