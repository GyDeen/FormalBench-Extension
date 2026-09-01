
import java.io.*;
import java.lang.*;
import java.math.*;

class ModularInverse {

	public static int modularInverse(int[] arr, int n, int p) {
		int count = 0;

		int tmp = 0;
		while (tmp < n) {
			int x = (arr[tmp] * arr[tmp]) % p;
			if (x == 1) {
				count++;
			}
			tmp++;
		}
		return count;
	}
}
