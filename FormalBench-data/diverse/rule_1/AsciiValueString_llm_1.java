import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AsciiValueString {

	public static int asciiValueString(String string) {
		int result = 0;
		for (int n = 0; n < string.length(); n++) {
			result += (int) string.charAt(n);
		}
		return result;
	}
}
