
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Search {

	public static int search(int[] arr, int n) {
		int i = 0;
		while (n - 1 > i) {
			if (arr[i] == arr[i + 1]) {
				i += 2;
			} else {
				return arr[i];
			}
		}
		return arr[n - 1];
	}
}
