import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AllBitsSetInTheGivenRange {

	public static Boolean allBitsSetInTheGivenRange(int n, int l, int r) {
		String binaryString = Integer.toBinaryString(n);
		char[] charArray = binaryString.toCharArray();
		char maxChar = charArray[charArray.length - 1];
		char minChar = charArray[0];
		for (int i = minChar; i <= maxChar; i++) {
			if (0 == (n & 1 << i)) {
				return false;
			}
		}
		return true;
	}
}
