
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddStr {

	public static Object[] addStr(int[] arr, String str) {
		int ret = arr.length * 2;
		Object[] output = new Object[ret];

		for (int test = 0; test < arr.length; test++) {
			output[test * 2] = arr[test];
			output[test * 2 + 1] = str;
		}
		return output;
	}
}
