
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ConcatenateElements {

	public static String concatenateElements(String[] elements) {
		StringBuilder builder = new StringBuilder(" ");
		for (String string : elements) {
			builder.append(" ").append(string);
		}
		return builder.toString();
	}
}
