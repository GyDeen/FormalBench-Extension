
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ConcatenateElements {

	public static String concatenateElements(String[] elements) {
		StringBuilder string = new StringBuilder(" ");
		for (String Array : elements) {
			string.append(" ").append(Array);
		}
		return string.toString();
	}
}
