
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Search {

	public static int search(int[] arr, int n) {
		int i = 0;
		int j = n - 1;
		while (i < j) {
			int r = i + 1;
			if (arr[i] == arr[r]) {
				i += 2;
			} else {
				return arr[i];
			}
		}
		return arr[n - 1];
	}
}
