import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Sum {

	public static int sum(int size) {
		int[] result = new int[size + 1];
		result[0] = 0;
		result[1] = 0;

		for (int p = 2; p <= size; p++) {
			if (result[p] == 0) {
				for (int k = p; k <= size; k += p) {
					result[k] += p;
				}
			}
		}

		return result[size];
	}
}
