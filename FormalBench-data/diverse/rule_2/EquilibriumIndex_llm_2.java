
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EquilibriumIndex {

	public static int equilibriumIndex(int[] arr) {
		int sum = 0;
		int leftSum = 0;

		for (int value : arr) {
			sum += value;
		}

		int left = 0;
		while (left < arr.length) {
			sum -= arr[left];
			if (leftSum == sum) {
				return left;
			}
			leftSum += arr[left];
			left++;
		}
		return -1;
	}
}
