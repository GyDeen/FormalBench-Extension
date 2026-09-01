
import java.io.*;
import java.lang.*;
import java.util.*;

class ShellSort {

	public static int[] shellSort(int[] myArray) {
		int my = myArray.length;
		int r = 1;
		while (r < my / 3) {
			r = 3 * r + 1;
		}
		while (r >= 1) {
			for (int p = r; p < my; p++) {
				for (int k = p; k >= r && myArray[k
						- r] > myArray[k]; k -= r) {
					int index = myArray[k];
					myArray[k] = myArray[k - r];
					myArray[k - r] = index;
				}
			}
			r = r / 3;
		}
		return myArray;
	}
}
