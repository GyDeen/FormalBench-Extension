import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchWordzMiddle {

	public static String textMatchWordzMiddle(String string) {
		String empty = "Not matched!";
		if (string.contains("z")) {
			int i = string.indexOf("z");
			if (i != 0 && i != string.length() - 1) {
				empty = "Found a match!";
			}
		}
		return empty;
	}
}
