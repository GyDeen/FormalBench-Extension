import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindRotations {

	public static int findRotations(String path) {
		int ret = 0;
		int[] arr = new int[26];
		for (int index = 0; index < path.length(); index++)
			arr[path.charAt(index) - 'a']++;
		for (int index = 0; index < 26; index++)
			if (arr[index] > 0)
				ret++;
		return ret;
	}
}
