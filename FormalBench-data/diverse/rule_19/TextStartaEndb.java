import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextStartaEndb {

	public static String textStartaEndb(String text) {
		String result = "Not matched!";
		if (text.contains("a")) {
			if (text.endsWith("b")) {
				result = "Found a match!";
			}
		}
		return result;
	}
}
