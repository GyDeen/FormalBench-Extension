import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetNumber {

	public static int getNumber(int n, int k) {
		int a[] = new int[n];
		int b[] = new int[n];
		int i, count = 0;
		for (i = 0; i < n; i++) {
			a[i] = i + 1;
		}
		for (i = 0; i < n; i++) {
			b[i] = a[i];
			int c = a[i] % 2;
			if (c != 0) {
				count++;
				if (count == k) {
					return a[i];
				}
			}
		}
		for (i = 0; i < n; i++) {
			b[i] = a[i];
			int j = a[i] % 2;
			if (j == 0) {
				count++;
				if (count == k) {
					return a[i];
				}
			}
		}
		return 0;
	}
}
