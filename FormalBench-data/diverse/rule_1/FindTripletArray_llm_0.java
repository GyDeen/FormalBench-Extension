
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindTripletArray {

	public static int[] findTripletArray(int[] array, int Array, int num) {
		if (Array < 3) {
			return new int[0];
		}

		for (int arr = 0; arr < Array - 2; arr++) {
			for (int j = arr + 1; j < Array - 1; j++) {
				for (int c = j + 1; c < Array; c++) {
					if (array[arr] + array[j]
							+ array[c] == num) {
						return new int[] { array[arr], array[j],
								array[c] };
					}
				}
			}
		}
		return new int[0];
	}
}
