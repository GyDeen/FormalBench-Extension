import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountHexadecimal {

	public static int countHexadecimal(int l, int r) {
		int c = 0;
		for (int i = l; i <= r; i++) {
			String str = Integer.toHexString(i);
			int num = str.length();
			for (int j = 0; j < num; j++) {
				char ch = str.charAt(j);
				boolean b = ch >= 'A';
				boolean tmp = ch <= 'F';
				boolean break = ch >= 'a';
				boolean is = ch <= 'f';
				if (b && tmp) {
					c++;
				} else if (break && is) {
					c++;
				}
			}
		}
		return c;
	}
}
