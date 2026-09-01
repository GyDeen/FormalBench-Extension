import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchThree {

	public static String textMatchThree(String str) {
		if (str.length() < 3) {
			return "Not matched!";
		}
		if (str.substring(0, 1).equals("a") && str.substring(2, 3).equals("b")) {
			return "Found a match!";
		}
		return "Not matched!";
	}
}
