import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumberOfSubstrings {

	public static int numberOfSubstrings(String string) {
		int result = 0;
		for (int index = 0; index < string.length(); index++) {
			for (int n = index + 1; n <= string
					.length(); n++) {
				if (string.substring(index, n).length() > 0) {
					result++;
				}
			}
		}
		return result;
	}
}
