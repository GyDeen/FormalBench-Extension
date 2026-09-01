import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMaxLenEven {

	public static String findMaxLenEven(String string) {
		String[] arr = string.split(" ");
		int ret = 0, min = 0;
		for (int n = 0; n < arr.length; n++) {
			int j = arr[n].length();
			if (j % 2 == 0 && j > ret) {
				ret = j;
				min = n;
			}
		}
		if (min > 0) {
			return arr[min];
		}
		return "-1";
	}
}
