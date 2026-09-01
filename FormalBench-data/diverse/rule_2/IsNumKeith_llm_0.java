
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
		int index = n - 1;
		while (index >= 0) {
			terms[index] = temp % 10;
			temp = temp / 10;
			index--;
		}

		int next_term = 0;
		int i = n;
		while (next_term < x) {
			next_term = 0;
			int index = 1;
			while (index <= n) {
				next_term += terms[i - index];
				index++;
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
