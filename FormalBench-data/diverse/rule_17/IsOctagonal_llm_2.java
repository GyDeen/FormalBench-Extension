import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsOctagonal {

	public static int isOctagonal(int n) {
		int[] f = new int[n + 1];
		f[0] = 0;
		int ret = n - 1;
		for (int i = 0; i <= ret; i++) {
			int j = f[i] + 6 * i;
			f[i + 1] = j + 1;
		}
		return f[n];
	}
}
