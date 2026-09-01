import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsNonagonal {

	public static int isNonagonal(int n) {
		int[] f = new int[n + 1];
		f[0] = 0;
		int p = n - 1;
		for (int i = 0; i <= p; i++) {
			int j = f[i] + 7 * i;
			f[i + 1] = j + 1;
		}
		return f[n];
	}
}
