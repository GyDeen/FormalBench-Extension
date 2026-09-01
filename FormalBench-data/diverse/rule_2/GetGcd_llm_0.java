
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetGcd {

	public static int getGcd(int[] arr) {
		int result = 0;
		int min = arr[0];
		int max = 1;
		while (max < arr.length) {
			if (arr[max] > min) {
				result = result > min ? result : min;
			} else {
				min = arr[max];
			}
			max++;
		}
		return result;
	}
}
