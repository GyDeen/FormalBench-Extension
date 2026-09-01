import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CatalanNumber {

	public static int catalanNumber(int num) {
		int[] catalan = new int[num + 1];
		catalan[0] = 1;
		int c = 1;
		while (c <= num) {
			catalan[c] = 0;
			for (int j = 0; j < c; j++) {
				catalan[c] += catalan[j] * catalan[c - j - 1];
			}
			c++;
		}
		return catalan[num];
	}
}
