
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetGcd {

	public static int getGcd(int[] arr) {
		int result = 0;
		int min = arr[0];
		int total = 1;
		while (total < arr.length) {
			if (arr[total] > min) {
				result = result > min ? result : min;
			} else {
				min = arr[total];
			}
			total++;
		}
		return result;
	}
}
