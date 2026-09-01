import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumberOfSubstrings {

	public static int numberOfSubstrings(String str) {
		int count = 0;
		for (int i = 0; str.length() > i; i++) {
			for (int j = i + 1; str.length() >= j; j++) {
				if (0 < str.substring(i, j).length()) {
					count++;
				}
			}
		}
		return count;
	}
}
