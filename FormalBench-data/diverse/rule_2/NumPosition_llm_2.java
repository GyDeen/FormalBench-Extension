import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumPosition {

	public static int numPosition(String text) {
		int pos = 0;
		while (pos < text.length()) {
			if (Character.isDigit(text.charAt(pos))) {
				return pos;
			}
			pos++;
		}
		return -1;
	}
}
