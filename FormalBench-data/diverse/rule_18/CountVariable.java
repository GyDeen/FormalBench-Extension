
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountVariable {

	public static String[] countVariable(int a, int b, int c, int d) {
		int totalSize = Math.max(0, a) + Math.max(0, b) + Math.max(0, c) + Math.max(0, d);
		String[] array = new String[totalSize];
		int index = 0;

		for (int i = 0; i < a; i++) {
			array[index] = "p";
			index++;
		}
		for (int i = 0; i < b; i++) {
			array[index] = "q";
			index++;
		}
		for (int i = 0; i < c; i++) {
			array[index] = "r";
			index++;
		}
		for (int i = 0; i < d; i++) {
			array[index] = "s";
			index++;
		}

		return array;
	}
}
