
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NthItems {

	public static int[] nthItems(int[] arr, int n) {
		int size = (arr.length + n - 1) / n;
		int[] result = new int[size];

		int index = 0, tmp = 0;
		while (index < arr.length) {
			result[tmp] = arr[index];
			index += n;
			tmp++;
		}
		return result;
	}
}
