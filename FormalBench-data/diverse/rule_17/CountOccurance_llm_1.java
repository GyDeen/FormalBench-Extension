import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountOccurance {

	public static int countOccurance(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			boolean b = s.charAt(i) == 's' && s.charAt(i + 1) == 't';
			boolean temp = s.charAt(i + 2) == 'd';
			if (b && temp) {
				count++;
			}
		}
		return count;
	}
}
