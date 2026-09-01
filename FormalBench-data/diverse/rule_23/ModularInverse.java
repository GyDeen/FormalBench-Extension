
import java.io.*;
import java.lang.*;
import java.math.*;

class ModularInverse {

	public static int modularInverse(int[] arr, int n, int p) {
		int count = 0;

		for (int i = 0; n > i; i++) {
			int x = (arr[i] * arr[i]) % p;
			if (x == 1) {
				count++;
			}
		}
		return count;
	}
}
