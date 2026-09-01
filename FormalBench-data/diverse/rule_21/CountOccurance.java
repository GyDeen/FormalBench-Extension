import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountOccurance {

	public static int countOccurance(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if ('s' == s.charAt(i) && 't' == s.charAt(i + 1) && 'd' == s.charAt(i + 2)) {
				count++;
			}
		}
		return count;
	}
}
