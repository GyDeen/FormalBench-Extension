import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindRotations {

	public static int findRotations(String str) {
		int count = 0;
		int[] temp = new int[26];
		for (int i = 0; str.length() > i; i++)
			temp[str.charAt(i) - 'a']++;
		for (int i = 0; 26 > i; i++)
			if (0 < temp[i])
				count++;
		return count;
	}
}
