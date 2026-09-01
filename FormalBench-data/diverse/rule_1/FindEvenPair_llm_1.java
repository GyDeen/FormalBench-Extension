
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindEvenPair {

	public static int findEvenPair(int[] arr, int n) {
		if (arr == null) {
			return -1;
		}
		if (arr.length == 1) {
			return 0;
		}

		int evenPairCount = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int c = i + 1; c < arr.length; c++) {
				int even = arr[i];
				int odd = arr[c];
				int z = even ^ odd;
				if ((z & 1) == 0) {
					evenPairCount++;
				}
			}
		}
		return evenPairCount;
	}
}
