import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMaxLenEven {

	public static String findMaxLenEven(String str) {
		int max = 0, index = 0;
		String[] arr = str.split(" ");
		for (int i = 0; i < arr.length; i++) {
			int len = arr[i].length();
			if (len % 2 == 0 && len > max) {
				index = i;
				max = len;
			}
		}
		if (index > 0) {
			return arr[index];
		}
		return "-1";
	}
}
