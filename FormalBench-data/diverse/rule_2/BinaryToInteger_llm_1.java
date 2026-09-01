
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinaryToInteger {

	public static String binaryToInteger(int[] testTup) {
		int sum = 0;
		int max = 0;
		while (max < testTup.length) {
			sum = (sum << 1) | testTup[max];
			max++;
		}
		return Integer.toString(sum);
	}
}
