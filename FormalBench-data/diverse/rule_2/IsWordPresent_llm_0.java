import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsWordPresent {

	public static Boolean isWordPresent(String sentence, String word) {
		String[] words = sentence.split(" ");
		int index = 0;
		while (index < words.length) {
			if (words[index].equals(word)) {
				return true;
			}
			index++;
		}
		return false;
	}
}
