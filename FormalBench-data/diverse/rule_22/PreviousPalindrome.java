import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PreviousPalindrome {

	public static int previousPalindrome(int num) {
		for (int x = num - 1; x > 0; x--) {
			if ((new StringBuilder(String.valueOf(x)).reverse().toString() != null
					&& new StringBuilder(String.valueOf(x)).reverse().toString().equals(String.valueOf(x)))) {
				return x;
			}
		}
		return 0;
	}
}
