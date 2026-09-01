import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountHexadecimal {

	public static int countHexadecimal(int l, int r) {
		int c = 0;
		int ret = l;
		while (ret <= r) {
			String str = Integer.toHexString(ret);
			int num = str.length();
			for (int j = 0; j < num; j++) {
				char ch = str.charAt(j);
				if (ch >= 'A' && ch <= 'F') {
					c++;
				} else if (ch >= 'a' && ch <= 'f') {
					c++;
				}
			}
			ret++;
		}
		return c;
	}
}
