
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultiplesOfNum {

	public static int[] multiplesOfNum(int array, int c) {
		int[] ret = new int[array];
		for (int r = 0; r < array; r++) {
			ret[r] = (r + 1) * c;
		}
		return ret;
	}
}
