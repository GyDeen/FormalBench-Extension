import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AsciiValueString {

	public static int asciiValueString(String k) {
		int asciiValue = 0;
		int value = 0;
		while (value < k.length()) {
			asciiValue += (int) k.charAt(value);
			value++;
		}
		return asciiValue;
	}
}
