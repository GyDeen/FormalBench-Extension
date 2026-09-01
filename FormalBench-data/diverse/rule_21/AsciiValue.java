import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AsciiValue {

	public static int asciiValue(String k) {
		if (1 == k.length()) {
			return (int) k.charAt(0);
		} else {
			return -1;
		}
	}
}
