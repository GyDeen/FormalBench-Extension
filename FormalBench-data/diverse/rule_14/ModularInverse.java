
import java.io.*;
import java.lang.*;
import java.math.*;

class ModularInverse {

	public static int modularInverse(int[] arr, int n, int p) {
		int count = 0;

		for (int i = 0; i < n; i++) {
			int x = (arr[i] * arr[i]) % p;
			if (!(x == 1))
				;
			else {
				count++;
			}
		}
		return count;
	}
}
