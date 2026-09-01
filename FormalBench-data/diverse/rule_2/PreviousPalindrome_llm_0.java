import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PreviousPalindrome {

	public static int previousPalindrome(int num) {
		int previous = num - 1;
		while (previous > 0) {
			if (String.valueOf(previous)
					.equals(new StringBuilder(String.valueOf(previous)).reverse().toString())) {
				return previous;
			}
			previous--;
		}
		return 0;
	}
}
