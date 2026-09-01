import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AsciiValueString {

	public static int asciiValueString(String value) {
		int result = 0;
		for (int j = 0; j < value.length(); j++) {
			result += (int) value.charAt(j);
		}
		return result;
	}
}
