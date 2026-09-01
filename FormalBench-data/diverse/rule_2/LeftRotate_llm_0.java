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
		int n = arr.length;
		int i = 0;
		while (i < d) {
			char temp = arr[0];
			int j = 0;
			while (j < n - 1) {
				arr[j] = arr[j + 1];
				j++;
			}
			arr[n - 1] = temp;
			i++;
		}
		return new String(arr);
	}
}
