import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CatalanNumber {

	public static int catalanNumber(int num) {
		int[] catalan = new int[num + 1];
		catalan[0] = 1;
		for (int i = 1; i <= num; i++) {
			catalan[i] = 0;
			for (int j = 0; j < i; j++) {
				catalan[i] = catalan[i] + (catalan[j] * catalan[i - j - 1]);
			}
		}
		return catalan[num];
	}
}
