
import java.io.*;
import java.lang.*;
import java.math.*;

class IsNumKeith {

	public static Boolean isNumKeith(int x) {
		int temp = x;
		int n = 0;
		while (temp > 0) {
			temp = temp / 10;
			n++;
		}

		int[] terms = new int[n];
		temp = x;
		int r = n - 1;
		while (r >= 0) {
			terms[r] = temp % 10;
			temp = temp / 10;
			r--;
		}

		int next_term = 0;
		int i = n;
		while (next_term < x) {
			next_term = 0;
			int r = 1;
			while (r <= n) {
				next_term += terms[i - r];
				r++;
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
