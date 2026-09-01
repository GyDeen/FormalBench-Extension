import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMaxLenEven {

	public static String findMaxLenEven(String str) {
		String[] arr = str.split(" ");
		int max = 0, index = 0;
		int min = 0;
		while (min < arr.length) {
			int len = arr[min].length();
			if (len % 2 == 0 && len > max) {
				max = len;
				index = min;
			}
			min++;
		}
		if (index > 0) {
			return arr[index];
		}
		return "-1";
	}
}
