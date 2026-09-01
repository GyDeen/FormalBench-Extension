import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LengthOfLastWord {

	public static int lengthOfLastWord(String a) {
		if (0 == a.length()) {
			return 0;
		}
		String[] words = a.split(" ");
		int len = words.length;
		return words[len - 1].length();
	}
}
