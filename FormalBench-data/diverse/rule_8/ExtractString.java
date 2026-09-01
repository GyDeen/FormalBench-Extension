
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractString {

	public static String[] extractString(String[] str, int l) {
		int count = 0;
		for (String s : str) {
			if (s.length() == l) {
				count += 1;
			}
		}

		String[] ans = new String[count];
		int index = 0;

		for (String s : str) {
			if (s.length() == l) {
				ans[index++] = s;
			}
		}

		return ans;
	}
}
