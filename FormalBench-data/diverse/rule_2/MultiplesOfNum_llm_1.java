
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultiplesOfNum {

	public static int[] multiplesOfNum(int m, int n) {
		int[] result = new int[m];
		int i = 0;
		while (i < m) {
			result[i] = (i + 1) * n;
			i++;
		}
		return result;
	}
}
