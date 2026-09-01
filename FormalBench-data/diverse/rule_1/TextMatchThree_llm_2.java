import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchThree {

	public static String textMatchThree(String regex) {
		if (regex.length() < 3) {
			return "Not matched!";
		}
		if (regex.substring(0, 1).equals("a") && regex.substring(2, 3).equals("b")) {
			return "Found a match!";
		}
		return "Not matched!";
	}
}
