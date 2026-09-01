
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddStr {

	public static Object[] addStr(int[] testTup, String k) {
		int resultLength = testTup.length * 2;
		Object[] result = new Object[resultLength];

		int start = 0;
		while (start < testTup.length) {
			result[start * 2] = testTup[start];
			result[start * 2 + 1] = k;
			start++;
		}
		return result;
	}
}
