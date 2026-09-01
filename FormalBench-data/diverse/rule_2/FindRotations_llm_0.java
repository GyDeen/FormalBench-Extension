import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindRotations {

	public static int findRotations(String str) {
		int count = 0;
		int[] temp = new int[26];
		int index = 0;
		while (index < str.length()) {
			temp[str.charAt(index) - 'a']++;
			index++;
		}
		int index = 0;
		while (index < 26) {
			if (temp[index] > 0)
				count++;
			index++;
		}
		return count;
	}
}
