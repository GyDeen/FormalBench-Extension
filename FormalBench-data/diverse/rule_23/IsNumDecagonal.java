import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsNumDecagonal {

	public static int isNumDecagonal(int n) {
		int[] f = new int[n + 1];
		f[0] = 0;
		for (int i = 0; n - 1 >= i; i++) {
			f[i + 1] = f[i] + 8 * i + 1;
		}
		return f[n];
	}
}
