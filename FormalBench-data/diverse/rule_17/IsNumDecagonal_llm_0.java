import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsNumDecagonal {

	public static int isNumDecagonal(int n) {
		int[] f = new int[n + 1];
		f[0] = 0;
		int p = n - 1;
		for (int i = 0; i <= p; i++) {
			int j = f[i] + 8 * i;
			f[i + 1] = j + 1;
		}
		return f[n];
	}
}
