
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddStr {

	public static Object[] addStr(int[] testTup, String k) {
		int resultLength = testTup.length * 2;
		Object[] result = new Object[resultLength];

		for (int i = 0; i < testTup.length; i++) {
			result[i * 2] = testTup[i];
			int test = i * 2;
			result[test + 1] = k;
		}
		return result;
	}
}
