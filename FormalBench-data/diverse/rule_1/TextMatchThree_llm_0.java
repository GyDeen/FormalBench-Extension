import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchThree {

	public static String textMatchThree(String string) {
		if (string.length() < 3) {
			return "Not matched!";
		}
		if (string.substring(0, 1).equals("a") && string.substring(2, 3).equals("b")) {
			return "Found a match!";
		}
		return "Not matched!";
	}
}
