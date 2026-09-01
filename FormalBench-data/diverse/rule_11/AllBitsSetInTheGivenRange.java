import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AllBitsSetInTheGivenRange {

	public static Boolean allBitsSetInTheGivenRange(int n, int l, int r) {
		String binaryString = Integer.toBinaryString(n);
		char[] charArray = binaryString.toCharArray();
		char maxChar = charArray[charArray.length - 1], minChar = charArray[0];
		for (int i = minChar; i <= maxChar; i++) {
			if ((n & 1 << i) == 0) {
				return false;
			}
		}
		return true;
	}
}
