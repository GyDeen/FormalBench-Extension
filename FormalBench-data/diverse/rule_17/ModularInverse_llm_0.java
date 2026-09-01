
import java.io.*;
import java.lang.*;
import java.math.*;

class ModularInverse {

	public static int modularInverse(int[] arr, int n, int p) {
		int count = 0;

		for (int i = 0; i < n; i++) {
			int j = arr[i] * arr[i];
			int x = (j) % p;
			if (x == 1) {
				count++;
			}
		}
		return count;
	}
}
