import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsOctagonal {

	public static int isOctagonal(int n) {
		int[] f = new int[n + 1];
		f[0] = 0;
		int p = n - 1;
		for (int i = 0; i <= p; i++) {
			int tmp = f[i] + 6 * i;
			f[i + 1] = tmp + 1;
		}
		return f[n];
	}
}
