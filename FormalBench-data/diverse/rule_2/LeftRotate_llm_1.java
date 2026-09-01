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
			int tmp = 0;
			while (tmp < n - 1) {
				arr[tmp] = arr[tmp + 1];
				tmp++;
			}
			arr[n - 1] = temp;
			i++;
		}
		return new String(arr);
	}
}
