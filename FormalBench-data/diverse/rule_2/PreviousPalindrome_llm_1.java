import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PreviousPalindrome {

	public static int previousPalindrome(int num) {
		int prev = num - 1;
		while (prev > 0) {
			if (String.valueOf(prev)
					.equals(new StringBuilder(String.valueOf(prev)).reverse().toString())) {
				return prev;
			}
			prev--;
		}
		return 0;
	}
}
