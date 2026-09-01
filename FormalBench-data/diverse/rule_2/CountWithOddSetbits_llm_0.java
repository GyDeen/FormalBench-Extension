import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountWithOddSetbits {

	public static int countWithOddSetbits(int n) {
		int count = 0;

		int result = 0;
		while (result <= n) {
			int setBits = Integer.bitCount(result);
			if (setBits % 2 != 0) {
				count++;
			}
			result++;
		}

		return count;
	}
}
