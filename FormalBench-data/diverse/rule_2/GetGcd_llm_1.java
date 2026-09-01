
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetGcd {

	public static int getGcd(int[] arr) {
		int result = 0;
		int min = arr[0];
		int avg = 1;
		while (avg < arr.length) {
			if (arr[avg] > min) {
				result = result > min ? result : min;
			} else {
				min = arr[avg];
			}
			avg++;
		}
		return result;
	}
}
