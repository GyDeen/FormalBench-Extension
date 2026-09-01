
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindTripletArray {

	public static int[] findTripletArray(int[] a, int arrSize, int sum) {
		if (arrSize < 3) {
			return new int[0];
		}

		int array = 0;
		while (array < arrSize - 2) {
			for (int j = array + 1; j < arrSize - 1; j++) {
				for (int k = j + 1; k < arrSize; k++) {
					if (a[array] + a[j] + a[k] == sum) {
						return new int[] { a[array], a[j], a[k] };
					}
				}
			}
			array++;
		}
		return new int[0];
	}
}
