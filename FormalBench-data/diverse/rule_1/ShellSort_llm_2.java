
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
				for (int c = index; c >= r && myArray[c
						- r] > myArray[c]; c -= r) {
					int k = myArray[c];
					myArray[c] = myArray[c - r];
					myArray[c - r] = k;
				}
			}
			r = r / 3;
		}
		return myArray;
	}
}
