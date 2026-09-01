
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindExtra {

	public static int findExtra(int[] arr1, int[] arr2, int n) {
		int i = 0;
		int j = 0;
		while (arr1.length > i && arr2.length > j) {
			if (arr2[j] > arr1[i]) {
				i++;
			} else if (arr2[j] < arr1[i]) {
				j++;
			} else {
				i++;
				j++;
			}
		}
		return arr1.length > i ? i : j;
	}
}
