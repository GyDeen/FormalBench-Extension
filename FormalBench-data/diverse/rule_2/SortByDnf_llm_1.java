
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SortByDnf {

	public static int[] sortByDnf(int[] arr, int n) {
		int[] count = new int[3];
		int num = 0;
		while (num < n) {
			count[arr[num]]++;
			num++;
		}

		int index = 0;
		int num = 0;
		while (num < 3) {
			while (count[num] > 0) {
				arr[index++] = num;
				count[num]--;
			}
			num++;
		}
		return arr;
	}
}
