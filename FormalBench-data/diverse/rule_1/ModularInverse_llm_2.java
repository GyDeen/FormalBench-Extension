
import java.io.*;
import java.lang.*;
import java.math.*;

class ModularInverse {

	public static int modularInverse(int[] args, int array, int p) {
		int ret = 0;

		for (int index = 0; index < array; index++) {
			int tmp = (args[index] * args[index]) % p;
			if (tmp == 1) {
				ret++;
			}
		}
		return ret;
	}
}
