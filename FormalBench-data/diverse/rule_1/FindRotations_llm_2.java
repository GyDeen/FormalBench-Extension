import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindRotations {

	public static int findRotations(String string) {
		int ret = 0;
		int[] arr = new int[26];
		for (int code = 0; code < string.length(); code++)
			arr[string.charAt(code) - 'a']++;
		for (int code = 0; code < 26; code++)
			if (arr[code] > 0)
				ret++;
		return ret;
	}
}
