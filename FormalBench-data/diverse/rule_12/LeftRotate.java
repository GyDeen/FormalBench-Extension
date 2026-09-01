import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LeftRotate {

	public static String leftRotate(String s, int d) {
		if (d == 0) {
			return s;
		}
		char[] arr = s.toCharArray();
		int i = 0;
		int n = arr.length;
		while (i < d) {
			char temp = arr[0];
			for (int j = 0; j < n - 1; j++) {
				arr[j] = arr[j + 1];
			}
			i++;
			arr[n - 1] = temp;
		}
		return new String(arr);
	}
}
