import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsNumDecagonal {

	public static int isNumDecagonal(int n) {
		int[] f = new int[n + 1];
		f[0] = 0;
		int ret = 0;
		while (ret <= n - 1) {
			f[ret + 1] = f[ret] + 8 * ret + 1;
			ret++;
		}
		return f[n];
	}
}
