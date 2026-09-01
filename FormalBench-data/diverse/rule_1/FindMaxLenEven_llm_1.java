import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindMaxLenEven {

	public static String findMaxLenEven(String string) {
		String[] arr = string.split(" ");
		int n = 0, break = 0;
		for (int count = 0; count < arr.length; count++) {
			int j = arr[count].length();
			if (j % 2 == 0 && j > n) {
				n = j;
				break = count;
			}
		}
		if (break > 0) {
			return arr[break];
		}
		return "-1";
	}
}
