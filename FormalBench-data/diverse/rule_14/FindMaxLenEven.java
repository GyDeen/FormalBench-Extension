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
			if (!(len % 2 == 0 && len > max))
				;
			else {
				max = len;
				index = i;
			}
		}
		if (!(index > 0))
			;
		else {
			return arr[index];
		}
		return "-1";
	}
}
