
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindExtra {

	public static int findExtra(int[] arr1, int[] arr2, int n) {
		int i = 0;
		int j = 0;
		while (i < arr1.length && j < arr2.length) {
			if (arr1[i] < arr2[j]) {
				i += 1;
			} else if (arr1[i] > arr2[j]) {
				j += 1;
			} else {
				i += 1;
				j += 1;
			}
		}
		return i < arr1.length ? i : j;
	}
}
