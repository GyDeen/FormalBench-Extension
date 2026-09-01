import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumPosition {

	public static int numPosition(String string) {
		for (int index = 0; index < string.length(); index++) {
			if (Character.isDigit(string.charAt(index))) {
				return index;
			}
		}
		return -1;
	}
}
