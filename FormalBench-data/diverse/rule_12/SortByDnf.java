
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SortByDnf {

	public static int[] sortByDnf(int[] arr, int n) {
		int[] count = new int[3];
		int index = 0;

		for (int i = 0; i < n; i++) {
			count[arr[i]]++;
		}
		for (int i = 0; i < 3; i++) {
			while (count[i] > 0) {
				arr[index++] = i;
				count[i]--;
			}
		}
		return arr;
	}
}
