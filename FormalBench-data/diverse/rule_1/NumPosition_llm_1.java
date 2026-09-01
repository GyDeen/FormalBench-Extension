import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumPosition {

	public static int numPosition(String str) {
		for (int index = 0; index < str.length(); index++) {
			if (Character.isDigit(str.charAt(index))) {
				return index;
			}
		}
		return -1;
	}
}
