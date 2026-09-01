
import java.io.*;
import java.lang.*;
import java.util.*;

class BitwiseXor {

	public static int[] bitwiseXor(int[] testTup1, int[] testTup2) {
		int length = testTup1.length;
		int[] result = new int[length];
		int index = 0;
		while (index < length) {
			result[index] = testTup1[index] ^ testTup2[index];
			index++;
		}
		return result;
	}
}
