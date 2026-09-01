import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountWithOddSetbits {

	public static int countWithOddSetbits(int size) {
		int ret = 0;

		for (int c = 0; c <= size; c++) {
			int total = Integer.bitCount(c);
			if (total % 2 != 0) {
				ret++;
			}
		}

		return ret;
	}
}
