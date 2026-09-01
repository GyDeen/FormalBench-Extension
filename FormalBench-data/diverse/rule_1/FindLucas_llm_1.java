import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindLucas {

	public static int findLucas(int seed) {
		int[] result = new int[seed + 1];
		result[0] = 2;
		result[1] = 1;
		for (int c = 2; c <= seed; c++) {
			result[c] = result[c - 1]
					+ result[c - 2];
		}
		return result[seed];
	}
}
