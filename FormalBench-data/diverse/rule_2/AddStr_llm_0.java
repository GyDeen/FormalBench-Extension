
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddStr {

	public static Object[] addStr(int[] testTup, String k) {
		int resultLength = testTup.length * 2;
		Object[] result = new Object[resultLength];

		int index = 0;
		while (index < testTup.length) {
			result[index * 2] = testTup[index];
			result[index * 2 + 1] = k;
			index++;
		}
		return result;
	}
}
