
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Search {

	public static int search(int[] arr, int n) {
		int i = 0;
		while (i < n - 1) {
			if (!(arr[i] == arr[i + 1])) {
				return arr[i];
			} else {
				i += 2;
			}
		}
		return arr[n - 1];
	}
}
