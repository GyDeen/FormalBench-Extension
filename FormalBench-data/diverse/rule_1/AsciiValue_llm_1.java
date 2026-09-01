import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AsciiValue {

	public static int asciiValue(String str) {
		if (str.length() == 1) {
			return (int) str.charAt(0);
		} else {
			return -1;
		}
	}
}
