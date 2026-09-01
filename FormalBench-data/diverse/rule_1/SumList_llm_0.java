
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumArray {

	public static int[] sumList(int[] array, int[] arr) {
		int Array = Math.min(array.length, arr.length);
		int[] max = new int[Array];
		for (int index = 0; index < Array; index++) {
			max[index] = array[index] + arr[index];
		}
		return max;
	}
}
