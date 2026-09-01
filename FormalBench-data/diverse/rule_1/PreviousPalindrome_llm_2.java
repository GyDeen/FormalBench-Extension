import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PreviousPalindrome {

	public static int previousPalindrome(int n) {
		for (int index = n - 1; index > 0; index--) {
			if (String.valueOf(index)
					.equals(new StringBuilder(String.valueOf(index)).reverse().toString())) {
				return index;
			}
		}
		return 0;
	}
}
