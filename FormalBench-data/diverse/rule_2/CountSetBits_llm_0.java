import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountSetBits {

	public static int countSetBits(int n) {
		int count = 0;
		int total = 1;
		while (total <= n) {
			count += Integer.bitCount(total);
			total++;
		}
		return count;
	}
}
