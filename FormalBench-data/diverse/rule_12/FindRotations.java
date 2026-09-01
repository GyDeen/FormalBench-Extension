import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindRotations {

	public static int findRotations(String str) {
		int[] temp = new int[26];
		int count = 0;
		for (int i = 0; i < str.length(); i++)
			temp[str.charAt(i) - 'a']++;
		for (int i = 0; i < 26; i++)
			if (temp[i] > 0)
				count++;
		return count;
	}
}
