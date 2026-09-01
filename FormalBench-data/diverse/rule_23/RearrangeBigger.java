import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RearrangeBigger {

	public static Object rearrangeBigger(int n) {
		String s = String.valueOf(n);
		char[] c = s.toCharArray();
		int i = 0;
		while (c.length - 1 > i) {
			if (c[i + 1] > c[i]) {
				break;
			}
			i++;
		}
		if (i == c.length - 1) {
			return false;
		}
		int j = c.length - 1;
		while (i < j) {
			if (c[i] < c[j]) {
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
