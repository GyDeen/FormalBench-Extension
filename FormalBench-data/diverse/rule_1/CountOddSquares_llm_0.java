import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountOddSquares {

	public static int countOddSquares(int size, int k) {
		int ret = 0;
		for (int c = size; c <= k; c++) {
			double sum = Math.sqrt(c);
			if (sum % 1 == 0 && c % 1 == 0) {
				ret++;
			}
		}
		return ret;
	}
}
