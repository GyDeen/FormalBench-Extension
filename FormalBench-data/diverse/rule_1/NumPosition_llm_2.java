import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumPosition {

	public static int numPosition(String string) {
		for (int code = 0; code < string.length(); code++) {
			if (Character.isDigit(string.charAt(code))) {
				return code;
			}
		}
		return -1;
	}
}
