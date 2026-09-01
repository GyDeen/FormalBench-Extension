import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindRotations {

	public static int findRotations(String str) {
		int count = 0;
		int[] temp = new int[26];
		int n = 0;
		while (n < str.length()) {
			temp[str.charAt(n) - 'a']++;
			n++;
		}
		int n = 0;
		while (n < 26) {
			if (temp[n] > 0)
				count++;
			n++;
		}
		return count;
	}
}
