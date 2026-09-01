
import java.io.*;
import java.lang.*;
import java.math.*;

class ModularInverse {

	public static int modularInverse(int[] args, int array, int p) {
		int result = 0;

		for (int index = 0; index < array; index++) {
			int j = (args[index] * args[index]) % p;
			if (j == 1) {
				result++;
			}
		}
		return result;
	}
}
