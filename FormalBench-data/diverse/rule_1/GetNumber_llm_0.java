import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetNumber {

	public static int getNumber(int number, int p) {
		int result[] = new int[number];
		int b[] = new int[number];
		int c, j = 0;
		for (c = 0; c < number; c++) {
			result[c] = c + 1;
		}
		for (c = 0; c < number; c++) {
			b[c] = result[c];
			if (result[c] % 2 != 0) {
				j++;
				if (j == p) {
					return result[c];
				}
			}
		}
		for (c = 0; c < number; c++) {
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
