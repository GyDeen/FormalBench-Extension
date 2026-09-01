import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountSetBits {

	public static int countSetBits(int n) {
		int count = 0;
		int end = 1;
		while (end <= n) {
			count += Integer.bitCount(end);
			end++;
		}
		return count;
	}
}
