
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
			for (int index = r; index < my; index++) {
				for (int k = index; k >= r && myArray[k
						- r] > myArray[k]; k -= r) {
					int count = myArray[k];
					myArray[k] = myArray[k - r];
					myArray[k - r] = count;
				}
			}
			r = r / 3;
		}
		return myArray;
	}
}
