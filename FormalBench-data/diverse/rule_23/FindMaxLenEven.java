import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMaxLenEven {

	public static String findMaxLenEven(String str) {
		String[] arr = str.split(" ");
		int max = 0, index = 0;
		for (int i = 0; arr.length > i; i++) {
			int len = arr[i].length();
			if (len % 2 == 0 && max < len) {
				max = len;
				index = i;
			}
		}
		if (0 < index) {
			return arr[index];
		}
		return "-1";
	}
}
