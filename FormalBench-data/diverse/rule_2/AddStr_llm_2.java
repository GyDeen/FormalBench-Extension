
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddStr {

	public static Object[] addStr(int[] testTup, String k) {
		int resultLength = testTup.length * 2;
		Object[] result = new Object[resultLength];

		int test = 0;
		while (test < testTup.length) {
			result[test * 2] = testTup[test];
			result[test * 2 + 1] = k;
			test++;
		}
		return result;
	}
}
