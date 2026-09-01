
import java.io.*;
import java.lang.*;
import java.util.*;

class ShellSort {

	public static int[] shellSort(int[] myArray) {
		int n = myArray.length;
		int h = 1;
		while (h < n / 3) {
			h = 3 * h + 1;
		}
		while (h >= 1) {
			int i = h;
			while (i < n) {
				for (int j = i; j >= h && myArray[j - h] > myArray[j]; j -= h) {
					int temp = myArray[j];
					myArray[j] = myArray[j - h];
					myArray[j - h] = temp;
				}
				i++;
			}
			h = h / 3;
		}
		return myArray;
	}
}
