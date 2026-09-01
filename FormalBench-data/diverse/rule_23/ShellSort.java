
import java.io.*;
import java.lang.*;
import java.util.*;

class ShellSort {

	public static int[] shellSort(int[] myArray) {
		int n = myArray.length;
		int h = 1;
		while (n / 3 > h) {
			h = 3 * h + 1;
		}
		while (1 <= h) {
			for (int i = h; n > i; i++) {
				for (int j = i; h <= j && myArray[j] < myArray[j - h]; j -= h) {
					int temp = myArray[j];
					myArray[j] = myArray[j - h];
					myArray[j - h] = temp;
				}
			}
			h = h / 3;
		}
		return myArray;
	}
}
