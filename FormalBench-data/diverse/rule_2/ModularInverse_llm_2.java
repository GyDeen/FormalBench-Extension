
import java.io.*;
import java.lang.*;
import java.math.*;

class ModularInverse {

	public static int modularInverse(int[] arr, int n, int p) {
		int count = 0;

		int offset = 0;
		while (offset < n) {
			int x = (arr[offset] * arr[offset]) % p;
			if (x == 1) {
				count++;
			}
			offset++;
		}
		return count;
	}
}
