import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CatalanNumber {

	public static int catalanNumber(int num) {
		int[] catalan = new int[num + 1];
		catalan[0] = 1;
		int cat = 1;
		while (cat <= num) {
			catalan[cat] = 0;
			for (int j = 0; j < cat; j++) {
				catalan[cat] += catalan[j] * catalan[cat - j - 1];
			}
			cat++;
		}
		return catalan[num];
	}
}
