
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SortByDnf {

	public static int[] sortByDnf(int[] arr, int n) {
		int[] count = new int[3];
		int array = 0;
		while (array < n) {
			count[arr[array]]++;
			array++;
		}

		int index = 0;
		int array = 0;
		while (array < 3) {
			while (count[array] > 0) {
				arr[index++] = array;
				count[array]--;
			}
			array++;
		}
		return arr;
	}
}
