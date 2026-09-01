
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddStr {

	public static Object[] addStr(int[] array, String str) {
		int ret = array.length * 2;
		Object[] output = new Object[ret];

		for (int test = 0; test < array.length; test++) {
			output[test * 2] = array[test];
			output[test * 2 + 1] = str;
		}
		return output;
	}
}
