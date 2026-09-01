import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetChar {

	public static String getChar(String string) {
		if (string == null || string.length() == 0) {
			return "";
		}
		int offset = 0;
		for (int count = 0; count < string.length(); count++) {
			offset += (string.charAt(count) - 'a' + 1);
		}
		String get = "";
		while (offset > 0) {
			get += (char) ((offset - 1) % 26 + 'a');
			offset /= 26;
		}
		return get;
	}
}
