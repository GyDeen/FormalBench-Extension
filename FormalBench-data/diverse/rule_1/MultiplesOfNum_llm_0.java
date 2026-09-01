
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultiplesOfNum {

	public static int[] multiplesOfNum(int num, int c) {
		int[] ret = new int[num];
		for (int r = 0; r < num; r++) {
			ret[r] = (r + 1) * c;
		}
		return ret;
	}
}
