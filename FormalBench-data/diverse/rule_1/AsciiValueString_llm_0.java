import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AsciiValueString {

	public static int asciiValueString(String string) {
		int result = 0;
		for (int j = 0; j < string.length(); j++) {
			result += (int) string.charAt(j);
		}
		return result;
	}
}
