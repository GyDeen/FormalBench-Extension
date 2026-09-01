import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMaxLenEven {

	public static String findMaxLenEven(String str) {
		String[] arr = str.split(" ");
		int max = 0, index = 0;
		int total = 0;
		while (total < arr.length) {
			int len = arr[total].length();
			if (len % 2 == 0 && len > max) {
				max = len;
				index = total;
			}
			total++;
		}
		if (index > 0) {
			return arr[index];
		}
		return "-1";
	}
}
