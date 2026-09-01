
import java.io.*;
import java.lang.*;
import java.math.*;

class IsNumKeith {

	public static Boolean isNumKeith(int x) {
		int temp = x;
		int n = 0;
		while (0 < temp) {
			temp = temp / 10;
			n++;
		}

		int[] terms = new int[n];
		temp = x;
		for (int i = n - 1; 0 <= i; i--) {
			terms[i] = temp % 10;
			temp = temp / 10;
		}

		int next_term = 0;
		int i = n;
		while (x > next_term) {
			next_term = 0;
			for (int j = 1; n >= j; j++) {
				next_term += terms[i - j];
			}
			if (terms.length <= i) {
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
