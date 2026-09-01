
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SortByDnf {

	public static int[] sortByDnf(int[] arr, int n) {
		int[] count = new int[3];
		int Array = 0;
		while (Array < n) {
			count[arr[Array]]++;
			Array++;
		}

		int index = 0;
		int Array = 0;
		while (Array < 3) {
			while (count[Array] > 0) {
				arr[index++] = Array;
				count[Array]--;
			}
			Array++;
		}
		return arr;
	}
}
