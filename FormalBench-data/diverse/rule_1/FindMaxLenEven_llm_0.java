import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMaxLenEven {

	public static String findMaxLenEven(String string) {
		String[] arr = string.split(" ");
		int ret = 0, break = 0;
		for (int n = 0; n < arr.length; n++) {
			int j = arr[n].length();
			if (j % 2 == 0 && j > ret) {
				ret = j;
				break = n;
			}
		}
		if (break > 0) {
			return arr[break];
		}
		return "-1";
	}
}
