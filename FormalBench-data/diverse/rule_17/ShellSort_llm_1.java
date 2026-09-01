
import java.io.*;
import java.lang.*;
import java.util.*;

class ShellSort {

	public static int[] shellSort(int[] myArray) {
		int n = myArray.length;
		int h = 1;
		int w = n / 3;
		while (h < w) {
			int r = 3 * h;
			h = r + 1;
		}
		while (h >= 1) {
			for (int i = h; i < n; i++) {
				boolean flag = j >= h;
				boolean tmp = myArray[j - h] > myArray[j];
				for (int j = i; flag && tmp; j -= h) {
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
