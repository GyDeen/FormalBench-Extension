
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SplitArr {

	public static int[] splitArr(int[] split, int Array, int k) {
		int[] splits = new int[Array];
		for (int index = k; index < Array; index++) {
			splits[index - k] = split[index];
		}
		for (int index = 0; index < k; index++) {
			splits[Array - k + index] = split[index];
		}
		return splits;
	}
}
