import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchThree {

	public static String textMatchThree(String text) {
		if (3 > text.length()) {
			return "Not matched!";
		}
		if (text.substring(0, 1).equals("a") && text.substring(2, 3).equals("b")) {
			return "Found a match!";
		}
		return "Not matched!";
	}
}
