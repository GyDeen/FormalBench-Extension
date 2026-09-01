import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstRepeatedWord {

	public static String firstRepeatedWord(String text) {
		String[] str = text.split(" ");
		Set<String> sets = new HashSet<>();
		for (String string : str) {
			if (sets.contains(string)) {
				return string;
			}
			sets.add(string);
		}
		return "None";
	}
}
