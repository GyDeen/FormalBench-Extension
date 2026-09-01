
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindExtra {

	public static int findExtra(int[] arr1, int[] arr2, int n) {
		int i = 0;
		int j = 0;
		boolean result = i < arr1.length;
		boolean tmp = j < arr2.length;
		while (result && tmp) {
			if (arr1[i] < arr2[j]) {
				i++;
			} else if (arr1[i] > arr2[j]) {
				j++;
			} else {
				i++;
				j++;
			}
		}
		return i < arr1.length ? i : j;
	}
}
