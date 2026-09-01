import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AsciiValueString {

	public static int asciiValueString(String k) {
		int asciiValue = 0;
		int i = 0;
		while (i < k.length()) {
			asciiValue += (int) k.charAt(i);
			i++;
		}
		return asciiValue;
	}
}
