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
		int min = minChar;
		while (min <= maxChar) {
			if ((n & 1 << min) == 0) {
				return false;
			}
			min++;
		}
		return true;
	}
}
