
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultiplesOfNum {

	public static int[] multiplesOfNum(int m, int n) {
		int[] result = new int[m];
		int num = 0;
		while (num < m) {
			result[num] = (num + 1) * n;
			num++;
		}
		return result;
	}
}
