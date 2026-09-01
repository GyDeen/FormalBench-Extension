import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchTwoThree {

	public static String textMatchTwoThree(String text) {
		if (text.length() < 3) {
			return "Not matched!";
		}
		if ("ab".equals(text.substring(0, 2))) {
			return "Found a match!";
		}
		return "Not matched!";
	}
}
