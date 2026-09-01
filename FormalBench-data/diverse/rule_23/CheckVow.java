import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckVow {

	public static int checkVow(String string, String vowels) {
		int count = 0;
		for (int i = 0; string.length() > i; i++) {
			char ch = string.charAt(i);
			if (-1 != vowels.indexOf(ch)) {
				count++;
			}
		}
		return count;
	}
}
