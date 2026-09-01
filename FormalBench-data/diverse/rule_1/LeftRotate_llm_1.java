import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LeftRotate {

	public static String leftRotate(String str, int a) {
		if (a == 0) {
			return str;
		}
		char[] arr = str.toCharArray();
		int len = arr.length;
		int r = 0;
		while (r < a) {
			char c = arr[0];
			for (int j = 0; j < len - 1; j++) {
				arr[j] = arr[j + 1];
			}
			arr[len - 1] = c;
			r++;
		}
		return new String(arr);
	}
}
