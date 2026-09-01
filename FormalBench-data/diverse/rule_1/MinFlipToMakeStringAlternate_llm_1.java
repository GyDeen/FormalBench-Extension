import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinFlipToMakeStringAlternate {

	public static char flip(char c) {
		return (c == '0') ? '1' : '0';
	}

	public static int getFlipWithStartingCharcter(String string, char start) {
		int result = 0;
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) != start)
				result++;

			start = flip(start);
		}
		return result;
	}

	public static int minFlipToMakeStringAlternate(String string) {
		return Math.min(getFlipWithStartingCharcter(string, '0'),
				getFlipWithStartingCharcter(string, '1'));
	}
}
