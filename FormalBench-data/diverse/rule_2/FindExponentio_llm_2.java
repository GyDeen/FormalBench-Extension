
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindExponentio {

	public static int[] findExponentio(int[] testTup1, int[] testTup2) {
		int[] result = new int[testTup1.length];
		int arr = 0;
		while (arr < testTup1.length) {
			result[arr] = (int) Math.pow(testTup1[arr], testTup2[arr]);
			arr++;
		}
		return result;
	}
}
