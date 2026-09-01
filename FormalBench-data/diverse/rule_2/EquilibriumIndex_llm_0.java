
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

		int right = 0;
		while (right < arr.length) {
			sum -= arr[right];
			if (leftSum == sum) {
				return right;
			}
			leftSum += arr[right];
			right++;
		}
		return -1;
	}
}
