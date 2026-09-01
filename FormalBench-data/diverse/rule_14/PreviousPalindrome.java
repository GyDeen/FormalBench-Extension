import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PreviousPalindrome {

	public static int previousPalindrome(int num) {
		for (int x = num - 1; x > 0; x--) {
			if (!(String.valueOf(x).equals(new StringBuilder(String.valueOf(x)).reverse().toString())))
				;
			else {
				return x;
			}
		}
		return 0;
	}
}
