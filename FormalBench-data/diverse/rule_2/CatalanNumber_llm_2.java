import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CatalanNumber {

	public static int catalanNumber(int num) {
		int[] catalan = new int[num + 1];
		catalan[0] = 1;
		int result = 1;
		while (result <= num) {
			catalan[result] = 0;
			for (int j = 0; j < result; j++) {
				catalan[result] += catalan[j] * catalan[result - j - 1];
			}
			result++;
		}
		return catalan[num];
	}
}
