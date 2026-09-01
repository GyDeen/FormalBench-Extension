
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumArray {

	public static int[] sumList(int[] arr1, int[] arr2) {
		int minLength = Math.min(arr1.length, arr2.length);
		int[] result = new int[minLength];
		int total = 0;
		while (total < minLength) {
			result[total] = arr1[total] + arr2[total];
			total++;
		}
		return result;
	}
}
