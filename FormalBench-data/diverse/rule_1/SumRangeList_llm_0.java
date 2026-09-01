
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumRangeArray {

	public static int sumRangeArray(int[] arr, int array, int c) {
		int num = 0;
		for (int index = array; index <= c; index++) {
			num += arr[index];
		}
		return num;
	}
}
