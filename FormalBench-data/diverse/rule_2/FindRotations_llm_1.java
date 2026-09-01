import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindRotations {

	public static int findRotations(String str) {
		int count = 0;
		int[] temp = new int[26];
		int offset = 0;
		while (offset < str.length()) {
			temp[str.charAt(offset) - 'a']++;
			offset++;
		}
		int offset = 0;
		while (offset < 26) {
			if (temp[offset] > 0)
				count++;
			offset++;
		}
		return count;
	}
}
