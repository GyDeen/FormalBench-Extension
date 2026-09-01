import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumberCtr {

	public static int numberCtr(String str) {
		int count = 0;
		int end = 0;
		while (end < str.length()) {
			if (Character.isDigit(str.charAt(end))) {
				count++;
			}
			end++;
		}
		return count;
	}
}
