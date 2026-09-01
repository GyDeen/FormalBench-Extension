import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PreviousPalindrome {

	public static int previousPalindrome(int p) {
		for (int i = p - 1; i > 0; i--) {
			if (String.valueOf(i)
					.equals(new StringBuilder(String.valueOf(i)).reverse().toString())) {
				return i;
			}
		}
		return 0;
	}
}
