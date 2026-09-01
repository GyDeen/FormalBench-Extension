import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumPosition {

	public static int numPosition(String text) {
		int result = 0;
		while (result < text.length()) {
			if (Character.isDigit(text.charAt(result))) {
				return result;
			}
			result++;
		}
		return -1;
	}
}
