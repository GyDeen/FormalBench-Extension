
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultiplesOfNum {

	public static int[] multiplesOfNum(int m, int n) {
		int[] result = new int[m];
		for (int i = 0; i < m; i++) {
			int j = i + 1;
			result[i] = (j) * n;
		}
		return result;
	}
}
