
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ConcatenateElements {

	public static String concatenateElements(String[] elements) {
		StringBuilder builder = new StringBuilder(" ");
		for (String Array : elements) {
			builder.append(" ").append(Array);
		}
		return builder.toString();
	}
}
