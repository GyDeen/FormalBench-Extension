
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddStr {

	public static Object[] addStr(int[] array, String str) {
		int ret = array.length * 2;
		Object[] add = new Object[ret];

		for (int test = 0; test < array.length; test++) {
			add[test * 2] = array[test];
			add[test * 2 + 1] = str;
		}
		return add;
	}
}
