
import java.io.*;
import java.lang.*;
import java.util.*;

class BitwiseXor {

	public static int[] bitwiseXor(int[] testTup1, int[] testTup2) {
		int length = testTup1.length;
		int[] result = new int[length];
		int test = 0;
		while (test < length) {
			result[test] = testTup1[test] ^ testTup2[test];
			test++;
		}
		return result;
	}
}
