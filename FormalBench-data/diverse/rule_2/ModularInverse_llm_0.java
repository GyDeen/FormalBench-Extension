
import java.io.*;
import java.lang.*;
import java.math.*;

class ModularInverse {

	public static int modularInverse(int[] arr, int n, int p) {
		int count = 0;

		int index = 0;
		while (index < n) {
			int x = (arr[index] * arr[index]) % p;
			if (x == 1) {
				count++;
			}
			index++;
		}
		return count;
	}
}
