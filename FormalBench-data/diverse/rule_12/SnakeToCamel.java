import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SnakeToCamel {

	public static String snakeToCamel(String word) {
		StringBuilder sb = new StringBuilder();
		String[] words = word.split("_");
		for (int i = 0; i < words.length; i++) {
			sb.append(words[i].substring(0, 1).toUpperCase());
			sb.append(words[i].substring(1).toLowerCase());
		}
		return sb.toString();
	}
}
