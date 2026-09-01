import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumberOfSubstrings {

	public static int numberOfSubstrings(String str) {
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			for (int j = i + 1; j <= str.length(); j++) {
				if (str.substring(i, j).length() > 0) {
					count += 1;
				}
			}
		}
		return count;
	}
}
