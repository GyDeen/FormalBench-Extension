import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindRotations {

	public static int findRotations(String str) {
		int count = 0;
		int[] temp = new int[26];
		for (int i = 0; i < str.length(); i++)
			temp[str.charAt(i) - 'a'] += 1;
		for (int i = 0; i < 26; i++)
			if (temp[i] > 0)
				count += 1;
		return count;
	}
}
