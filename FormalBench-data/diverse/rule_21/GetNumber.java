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
			if (a[i] % 2 != 0) {
				count++;
				if (k == count) {
					return a[i];
				}
			}
		}
		for (i = 0; i < n; i++) {
			b[i] = a[i];
			if (0 == a[i] % 2) {
				count++;
				if (k == count) {
					return a[i];
				}
			}
		}
		return 0;
	}
}
