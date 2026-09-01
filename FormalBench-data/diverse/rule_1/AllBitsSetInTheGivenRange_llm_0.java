import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AllBitsSetInTheGivenRange {

	public static Boolean allBitsSetInTheGivenRange(int n, int k, int m) {
		String binaryString = Integer.toBinaryString(n);
		char[] chars = binaryString.toCharArray();
		char c = chars[chars.length - 1];
		char min = chars[0];
		for (int i = min; i <= c; i++) {
			if ((n & 1 << i) == 0) {
				return false;
			}
		}
		return true;
	}
}
