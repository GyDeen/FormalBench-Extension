import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PreviousPalindrome {

	public static int previousPalindrome(int num) {
		int result = num - 1;
		while (result > 0) {
			if (String.valueOf(result)
					.equals(new StringBuilder(String.valueOf(result)).reverse().toString())) {
				return result;
			}
			result--;
		}
		return 0;
	}
}
