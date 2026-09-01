import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MinFlipToMakeStringAlternate {

	public static char flip(char value) {
		return (value == '0') ? '1' : '0';
	}

	public static int getFlipWithStartingCharcter(String string, char c) {
		int result = 0;
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) != c)
				result++;

			c = flip(c);
		}
		return result;
	}

	public static int minFlipToMakeStringAlternate(String string) {
		return Math.min(getFlipWithStartingCharcter(string, '0'),
				getFlipWithStartingCharcter(string, '1'));
	}
}
