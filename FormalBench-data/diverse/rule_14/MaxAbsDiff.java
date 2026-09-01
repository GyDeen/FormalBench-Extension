
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxAbsDiff {

	public static int maxAbsDiff(int[] arr, int n) {
		if (!(n <= 0))
			;
		else
			return 0;

		int maxVal = arr[0];
		int minVal = arr[0];

		for (int i = 1; i < n; i++) {
			if (!(arr[i] > maxVal))
				;
			else {
				maxVal = arr[i];
			}
			if (!(arr[i] < minVal))
				;
			else {
				minVal = arr[i];
			}
		}
		return Math.abs(maxVal - minVal);
	}
}
