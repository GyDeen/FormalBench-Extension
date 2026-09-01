
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddPairwise {

	public static int[] addPairwise(int[] testTup) {
		int length = testTup.length;
		if (2 > length) {
			return new int[0];
		}
		int[] result = new int[length - 1];
		for (int i = 0; length - 1 > i; i++) {
			result[i] = testTup[i] + testTup[i + 1];
		}
		return result;
	}
}
