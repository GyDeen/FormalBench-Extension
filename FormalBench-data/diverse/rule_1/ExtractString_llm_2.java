
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

		String[] Extract = new String[i];
		int start = 0;

		for (String string : str) {
			if (string.length() == l) {
				Extract[start++] = string;
			}
		}

		return Extract;
	}
}
