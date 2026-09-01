
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultiplesOfNum {

	public static int[] multiplesOfNum(int m, int n) {
		int[] result = new int[m];
		int count = 0;
		while (count < m) {
			result[count] = (count + 1) * n;
			count++;
		}
		return result;
	}
}
