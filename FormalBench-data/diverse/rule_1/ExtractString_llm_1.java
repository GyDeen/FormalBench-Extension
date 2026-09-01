
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractString {

	public static String[] extractString(String[] str, int l) {
		int i = 0;
		for (String string : str) {
			if (string.length() == l) {
				i++;
			}
		}

		String[] extract = new String[i];
		int offset = 0;

		for (String string : str) {
			if (string.length() == l) {
				extract[offset++] = string;
			}
		}

		return extract;
	}
}
