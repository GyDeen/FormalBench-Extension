import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountWithOddSetbits {

	public static int countWithOddSetbits(int n) {
		int count = 0;

		int ret = 0;
		while (ret <= n) {
			int setBits = Integer.bitCount(ret);
			if (setBits % 2 != 0) {
				count++;
			}
			ret++;
		}

		return count;
	}
}
