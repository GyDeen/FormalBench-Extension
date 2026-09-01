
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BinaryToInteger {

	public static String binaryToInteger(int[] testTup) {
		int sum = 0;
		int num = 0;
		while (num < testTup.length) {
			sum = (sum << 1) | testTup[num];
			num++;
		}
		return Integer.toString(sum);
	}
}
