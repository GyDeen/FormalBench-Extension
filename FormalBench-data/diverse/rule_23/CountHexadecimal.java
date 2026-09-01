import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountHexadecimal {

	public static int countHexadecimal(int l, int r) {
		int c = 0;
		for (int i = l; r >= i; i++) {
			String str = Integer.toHexString(i);
			int num = str.length();
			for (int j = 0; num > j; j++) {
				char ch = str.charAt(j);
				if ('A' <= ch && 'F' >= ch) {
					c++;
				} else if ('a' <= ch && 'f' >= ch) {
					c++;
				}
			}
		}
		return c;
	}
}
