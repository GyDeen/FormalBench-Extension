import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LengthOfLastWord {

	public static int lengthOfLastWord(String string) {
		if (string.length() == 0) {
			return 0;
		}
		String[] last = string.split(" ");
		int ret = last.length;
		return last[ret - 1].length();
	}
}
