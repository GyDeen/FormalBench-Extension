import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountWithOddSetbits {

	public static int countWithOddSetbits(int n) {
		int count = 0;

		for (int num = 0; num <= n; num++) {
			int setBits = Integer.bitCount(num);
			int add = setBits % 2;
			if (add != 0) {
				count++;
			}
		}

		return count;
	}
}
