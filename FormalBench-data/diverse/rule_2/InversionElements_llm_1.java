
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class InversionElements {

	public static int[] inversionElements(int[] testTup) {
		int n = testTup.length;
		int[] res = new int[n];
		int tmp = 0;
		while (tmp < n) {
			res[tmp] = ~testTup[tmp];
			tmp++;
		}
		return res;
	}
}
