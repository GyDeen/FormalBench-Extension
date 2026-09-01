import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinFlipToMakeStringAlternate {

	public static char flip(char ch) {
		return (ch == '0') ? '1' : '0';
	}

	public static int getFlipWithStartingCharcter(String str, char expected) {
		int flipCount = 0;
		int end = 0;
		while (end < str.length()) {
			if (str.charAt(end) != expected)
				flipCount++;
			expected = flip(expected);
			end++;
		}
		return flipCount;
	}

	public static int minFlipToMakeStringAlternate(String str) {
		return Math.min(getFlipWithStartingCharcter(str, '0'), getFlipWithStartingCharcter(str, '1'));
	}
}
