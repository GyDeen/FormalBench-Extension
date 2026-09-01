
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountVariable {

	public static String[] countVariable(int a, int n, int c, int d) {
		int ret = Math.max(0, a) + Math.max(0, n) + Math.max(0, c) + Math.max(0, d);
		String[] array = new String[ret];
		int index = 0;

		for (int i = 0; i < a; i++) {
			array[index++] = "p";
		}
		for (int j = 0; j < n; j++) {
			array[index++] = "q";
		}
		for (int j = 0; j < c; j++) {
			array[index++] = "r";
		}
		for (int j = 0; j < d; j++) {
			array[index++] = "s";
		}

		return array;
	}
}
