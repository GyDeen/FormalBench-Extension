
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindExponentio {

	public static int[] findExponentio(int[] testTup1, int[] testTup2) {
		int[] result = new int[testTup1.length];
		int test = 0;
		while (test < testTup1.length) {
			result[test] = (int) Math.pow(testTup1[test], testTup2[test]);
			test++;
		}
		return result;
	}
}
