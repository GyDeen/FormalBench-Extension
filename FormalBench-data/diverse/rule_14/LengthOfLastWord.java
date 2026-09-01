import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LengthOfLastWord {

	public static int lengthOfLastWord(String a) {
		if (!(a.length() == 0))
			;
		else {
			return 0;
		}
		String[] words = a.split(" ");
		int len = words.length;
		return words[len - 1].length();
	}
}
