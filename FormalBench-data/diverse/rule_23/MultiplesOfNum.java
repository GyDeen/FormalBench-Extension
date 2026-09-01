
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultiplesOfNum {

	public static int[] multiplesOfNum(int m, int n) {
		int[] result = new int[m];
		for (int i = 0; m > i; i++) {
			result[i] = (i + 1) * n;
		}
		return result;
	}
}
