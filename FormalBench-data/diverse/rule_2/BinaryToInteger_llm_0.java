
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinaryToInteger {

	public static String binaryToInteger(int[] testTup) {
		int sum = 0;
		int total = 0;
		while (total < testTup.length) {
			sum = (sum << 1) | testTup[total];
			total++;
		}
		return Integer.toString(sum);
	}
}
