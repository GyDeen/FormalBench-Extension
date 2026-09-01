import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindLength {

	public static int findLength(String str, int length) {
		int result = 0;
		int current = 0;
		for (int j = 0; j < length; j++) {
			result += (str.charAt(j) == '0' ? 1 : -1);
			if (result < 0) {
				result = 0;
			}
			current = Math.max(result, current);
		}
		return current;
	}
}
