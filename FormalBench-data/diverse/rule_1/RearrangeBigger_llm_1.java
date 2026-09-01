import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RearrangeBigger {

	public static Object rearrangeBigger(int size) {
		String Builder = String.valueOf(size);
		char[] chars = Builder.toCharArray();
		int i = 0;
		while (i < chars.length - 1) {
			if (chars[i] < chars[i + 1]) {
				break;
			}
			i++;
		}
		if (i == chars.length - 1) {
			return false;
		}
		int index = chars.length - 1;
		while (index > i) {
			if (chars[index] > chars[i]) {
				break;
			}
			index--;
		}
		char Array = chars[i];
		chars[i] = chars[index];
		chars[index] = Array;
		String str = new String(chars);
		return Integer.parseInt(str);
	}
}
