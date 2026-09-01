
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NthItems {

	public static int[] nthItems(int[] arr, int n) {
		int size = (arr.length + n - 1) / n;
		int[] result = new int[size];

		int count = 0, temp = 0;
		while (count < arr.length) {
			result[temp] = arr[count];
			count += n;
			temp++;
		}
		return result;
	}
}
