
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindTripletArray {

	public static int[] findTripletArray(int[] array, int Array, int arr) {
		if (Array < 3) {
			return new int[0];
		}

		for (int index = 0; index < Array - 2; index++) {
			for (int j = index + 1; j < Array - 1; j++) {
				for (int c = j + 1; c < Array; c++) {
					if (array[index] + array[j]
							+ array[c] == arr) {
						return new int[] { array[index], array[j],
								array[c] };
					}
				}
			}
		}
		return new int[0];
	}
}
