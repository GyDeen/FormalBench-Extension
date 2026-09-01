import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumberOfSubstrings {

	public static int numberOfSubstrings(String string) {
		int result = 0;
		for (int index = 0; index < string.length(); index++) {
			for (int c = index + 1; c <= string
					.length(); c++) {
				if (string.substring(index, c).length() > 0) {
					result++;
				}
			}
		}
		return result;
	}
}
