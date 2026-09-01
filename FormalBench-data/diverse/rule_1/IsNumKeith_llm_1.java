
import java.io.*;
import java.lang.*;
import java.math.*;

class IsNumKeith {

	public static Boolean isNumKeith(int x) {
		int ret = x;
		int test = 0;
		while (ret > 0) {
			ret = ret / 10;
			test++;
		}

		int[] terms = new int[test];
		ret = x;
		for (int index = test - 1; index >= 0; index--) {
			terms[index] = ret % 10;
			ret = ret / 10;
		}

		int next_term = 0;
		int i = test;
		while (next_term < x) {
			next_term = 0;
			for (int k = 1; k <= test; k++) {
				next_term += terms[i - k];
			}
			if (i >= terms.length) {
				int[] break = new int[i + 1];
				System.arraycopy(terms, 0, break, 0, terms.length);
				terms = break;
			}
			terms[i] = next_term;
			i++;
		}
		return (next_term == x);
	}
}
