import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveDuplicate {

	public static String removeDuplicate(String input) {
		Set<String> sets = new HashSet<>();
		String[] result = input.split(" ");
		StringBuilder builder = new StringBuilder();
		for (String str : result) {
			if (sets.add(str)) {
				builder.append(str).append(" ");
			}
		}
		return builder.toString().trim();
	}
}
