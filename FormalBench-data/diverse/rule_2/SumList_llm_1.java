
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumArray {

	public static int[] sumList(int[] arr1, int[] arr2) {
		int minLength = Math.min(arr1.length, arr2.length);
		int[] result = new int[minLength];
		int index = 0;
		while (index < minLength) {
			result[index] = arr1[index] + arr2[index];
			index++;
		}
		return result;
	}
}
