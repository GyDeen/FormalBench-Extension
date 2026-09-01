
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Search {

	public static int search(int[] arr, int n) {
		int i = 0;
		int len = n - 1;
		while (i < len) {
			int tmp = i + 1;
			if (arr[i] == arr[tmp]) {
				i += 2;
			} else {
				return arr[i];
			}
		}
		return arr[n - 1];
	}
}
