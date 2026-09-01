import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AsciiValue {

	public static int asciiValue(String string) {
		if (string.length() == 1) {
			return (int) string.charAt(0);
		} else {
			return -1;
		}
	}
}
