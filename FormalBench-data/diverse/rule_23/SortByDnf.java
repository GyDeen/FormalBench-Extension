
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SortByDnf {

	public static int[] sortByDnf(int[] arr, int n) {
		int[] count = new int[3];
		for (int i = 0; n > i; i++) {
			count[arr[i]]++;
		}

		int index = 0;
		for (int i = 0; 3 > i; i++) {
			while (0 < count[i]) {
				arr[index++] = i;
				count[i]--;
			}
		}
		return arr;
	}
}
