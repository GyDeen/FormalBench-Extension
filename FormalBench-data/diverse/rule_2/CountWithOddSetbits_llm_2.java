import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountWithOddSetbits {

	public static int countWithOddSetbits(int n) {
		int count = 0;

		int end = 0;
		while (end <= n) {
			int setBits = Integer.bitCount(end);
			if (setBits % 2 != 0) {
				count++;
			}
			end++;
		}

		return count;
	}
}
