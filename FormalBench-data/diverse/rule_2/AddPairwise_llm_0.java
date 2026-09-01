
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
		int index = 0;
		while (index < length - 1) {
			result[index] = testTup[index] + testTup[index + 1];
			index++;
		}
		return result;
	}
}
