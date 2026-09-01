import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsNumDecagonal {

	public static int isNumDecagonal(int n) {
		int[] f = new int[n + 1];
		f[0] = 0;
		int p = 0;
		while (p <= n - 1) {
			f[p + 1] = f[p] + 8 * p + 1;
			p++;
		}
		return f[n];
	}
}
