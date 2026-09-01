import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AsciiValueString {

	public static int asciiValueString(String k) {
		int asciiValue = 0;
		int result = 0;
		while (result < k.length()) {
			asciiValue += (int) k.charAt(result);
			result++;
		}
		return asciiValue;
	}
}
