import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountWithOddSetbits {

	public static int countWithOddSetbits(int n) {
		int count = 0;

		for (int num = 0; n >= num; num++) {
			int setBits = Integer.bitCount(num);
			if (0 != setBits % 2) {
				count++;
			}
		}

		return count;
	}
}
