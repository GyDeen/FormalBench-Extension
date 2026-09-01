
import java.io.*;
import java.lang.*;
import java.util.*;

class BitwiseXor {

	public static int[] bitwiseXor(int[] testTup1, int[] testTup2) {
		int length = testTup1.length;
		int[] result = new int[length];
		int arr = 0;
		while (arr < length) {
			result[arr] = testTup1[arr] ^ testTup2[arr];
			arr++;
		}
		return result;
	}
}
