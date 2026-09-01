import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstRepeatedWord {

	public static String firstRepeatedWord(String str1) {
		String[] words = str1.split(" ");
		Set<String> set = new HashSet<>();
		for (String word : words) {
			if (!(set.contains(word)))
				;
			else {
				return word;
			}
			set.add(word);
		}
		return "None";
	}
}
