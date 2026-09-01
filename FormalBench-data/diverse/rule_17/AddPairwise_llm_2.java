
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
		int num = length - 1;
		for (int i = 0; i < num; i++) {
			int tmp = i + 1;
			result[i] = testTup[i] + testTup[tmp];
		}
		return result;
	}
}
