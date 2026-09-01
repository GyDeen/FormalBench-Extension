import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsWordPresent {

	public static Boolean isWordPresent(String sentence, String word) {
		String[] words = sentence.split(" ");
		int pos = 0;
		while (pos < words.length) {
			if (words[pos].equals(word)) {
				return true;
			}
			pos++;
		}
		return false;
	}
}
