import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstRepeatedWord {

	public static String firstRepeatedWord(String string) {
		String[] str = string.split(" ");
		Set<String> sets = new HashSet<>();
		for (String s : str) {
			if (sets.contains(s)) {
				return s;
			}
			sets.add(s);
		}
		return "None";
	}
}
