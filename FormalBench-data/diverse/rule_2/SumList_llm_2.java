
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumArray {

	public static int[] sumList(int[] arr1, int[] arr2) {
		int minLength = Math.min(arr1.length, arr2.length);
		int[] result = new int[minLength];
		int num = 0;
		while (num < minLength) {
			result[num] = arr1[num] + arr2[num];
			num++;
		}
		return result;
	}
}
