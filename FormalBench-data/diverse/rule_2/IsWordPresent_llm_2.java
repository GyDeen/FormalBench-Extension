import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsWordPresent {

	public static Boolean isWordPresent(String sentence, String word) {
		String[] words = sentence.split(" ");
		int n = 0;
		while (n < words.length) {
			if (words[n].equals(word)) {
				return true;
			}
			n++;
		}
		return false;
	}
}
