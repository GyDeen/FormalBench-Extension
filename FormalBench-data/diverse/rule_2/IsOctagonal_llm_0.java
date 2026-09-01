import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsOctagonal {

	public static int isOctagonal(int n) {
		int[] f = new int[n + 1];
		f[0] = 0;
		int p = 0;
		while (p <= n - 1) {
			f[p + 1] = f[p] + 6 * p + 1;
			p++;
		}
		return f[n];
	}
}
