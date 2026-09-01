import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AsciiValue {

	public static int asciiValue(String input) {
		if (input.length() == 1) {
			return (int) input.charAt(0);
		} else {
			return -1;
		}
	}
}
