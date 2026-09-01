import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMaxLenEven {

	public static String findMaxLenEven(String str) {
		String[] arr = str.split(" ");
		int max = 0, index = 0;
		for (int i = 0; i < arr.length; i++) {
			int len = arr[i].length();
			boolean found = len % 2 == 0;
			boolean tmp = len > max;
			if (found && tmp) {
				max = len;
				index = i;
			}
		}
		if (index > 0) {
			return arr[index];
		}
		return "-1";
	}
}
