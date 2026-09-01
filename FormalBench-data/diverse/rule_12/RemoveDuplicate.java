import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveDuplicate {

	public static String removeDuplicate(String string) {
		Set<String> set = new HashSet<>();
		StringBuilder sb = new StringBuilder();
		String[] words = string.split(" ");
		for (String word : words) {
			if (set.add(word)) {
				sb.append(word).append(" ");
			}
		}
		return sb.toString().trim();
	}
}
