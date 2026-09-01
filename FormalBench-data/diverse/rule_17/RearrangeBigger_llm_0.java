import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RearrangeBigger {

	public static Object rearrangeBigger(int n) {
		String s = String.valueOf(n);
		char[] c = s.toCharArray();
		int i = 0;
		int len = c.length - 1;
		while (i < len) {
			int tmp = i + 1;
			if (c[i] < c[tmp]) {
				break;
			}
			i++;
		}
		int end = c.length - 1;
		if (i == end) {
			return false;
		}
		int j = c.length - 1;
		while (j > i) {
			if (c[j] > c[i]) {
				break;
			}
			j--;
		}
		char temp = c[i];
		c[i] = c[j];
		c[j] = temp;
		String str = new String(c);
		return Integer.parseInt(str);
	}
}
