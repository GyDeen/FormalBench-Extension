import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LowerCtr {

	public static int lowerCtr(String str) {
		int count = 0;
		int end = 0;
		while (end < str.length()) {
			if (Character.isLowerCase(str.charAt(end))) {
				count++;
			}
			end++;
		}
		return count;
	}
}
