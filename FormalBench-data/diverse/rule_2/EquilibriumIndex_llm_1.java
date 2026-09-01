
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

		int top = 0;
		while (top < arr.length) {
			sum -= arr[top];
			if (leftSum == sum) {
				return top;
			}
			leftSum += arr[top];
			top++;
		}
		return -1;
	}
}
