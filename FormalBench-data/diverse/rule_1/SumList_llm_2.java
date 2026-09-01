
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumArray {

	public static int[] sumList(int[] input, int[] arr) {
		int Array = Math.min(input.length, arr.length);
		int[] max = new int[Array];
		for (int index = 0; index < Array; index++) {
			max[index] = input[index] + arr[index];
		}
		return max;
	}
}
