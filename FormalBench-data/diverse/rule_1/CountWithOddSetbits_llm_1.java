import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountWithOddSetbits {

	public static int countWithOddSetbits(int size) {
		int ret = 0;

		for (int i = 0; i <= size; i++) {
			int total = Integer.bitCount(i);
			if (total % 2 != 0) {
				ret++;
			}
		}

		return ret;
	}
}
