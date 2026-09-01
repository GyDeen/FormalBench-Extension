import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetNumber {

	public static int getNumber(int value, int p) {
		int result[] = new int[value];
		int b[] = new int[value];
		int c, j = 0;
		for (c = 0; c < value; c++) {
			result[c] = c + 1;
		}
		for (c = 0; c < value; c++) {
			b[c] = result[c];
			if (result[c] % 2 != 0) {
				j++;
				if (j == p) {
					return result[c];
				}
			}
		}
		for (c = 0; c < value; c++) {
			b[c] = result[c];
			if (result[c] % 2 == 0) {
				j++;
				if (j == p) {
					return result[c];
				}
			}
		}
		return 0;
	}
}
