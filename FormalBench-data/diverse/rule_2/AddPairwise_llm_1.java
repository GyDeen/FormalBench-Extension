
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
		int num = 0;
		while (num < length - 1) {
			result[num] = testTup[num] + testTup[num + 1];
			num++;
		}
		return result;
	}
}
