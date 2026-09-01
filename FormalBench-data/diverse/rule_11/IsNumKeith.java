
import java.io.*;
import java.lang.*;
import java.math.*;

class IsNumKeith {

	public static Boolean isNumKeith(int x) {
		int temp = x, n = 0;
		while (temp > 0) {
			temp = temp / 10;
			n++;
		}

		int[] terms = new int[n];
		temp = x;
		for (int i = n - 1; i >= 0; i--) {
			terms[i] = temp % 10;
			temp = temp / 10;
		}

		int next_term = 0, i = n;
		while (next_term < x) {
			next_term = 0;
			for (int j = 1; j <= n; j++) {
				next_term += terms[i - j];
			}
			if (i >= terms.length) {
				int[] newTerms = new int[i + 1];
				System.arraycopy(terms, 0, newTerms, 0, terms.length);
				terms = newTerms;
			}
			terms[i] = next_term;
			i++;
		}
		return (next_term == x);
	}
}
