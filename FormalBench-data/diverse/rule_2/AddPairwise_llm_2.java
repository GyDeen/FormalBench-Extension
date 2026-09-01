
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddPairwise {

	public static int[] addPairwise(int[] testTup) {
		int length = testTup.length;
		if (length < 2) {
			return new int[0];
		}
		int[] result = new int[length - 1];
		int test = 0;
		while (test < length - 1) {
			result[test] = testTup[test] + testTup[test + 1];
			test++;
		}
		return result;
	}
}
