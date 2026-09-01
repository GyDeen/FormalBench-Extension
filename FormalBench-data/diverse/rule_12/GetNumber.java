import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetNumber {

	public static int getNumber(int n, int k) {
		int a[] = new int[n];
		int i, count = 0;
		int b[] = new int[n];
		for (i = 0; i < n; i++) {
			a[i] = i + 1;
		}
		for (i = 0; i < n; i++) {
			b[i] = a[i];
			if (a[i] % 2 != 0) {
				count++;
				if (count == k) {
					return a[i];
				}
			}
		}
		for (i = 0; i < n; i++) {
			b[i] = a[i];
			if (a[i] % 2 == 0) {
				count++;
				if (count == k) {
					return a[i];
				}
			}
		}
		return 0;
	}
}
