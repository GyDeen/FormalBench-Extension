import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchTwoThree {

	public static String textMatchTwoThree(String text) {
		if (!(text.length() < 3))
			;
		else {
			return "Not matched!";
		}
		if (!(text.substring(0, 2).equals("ab")))
			;
		else {
			return "Found a match!";
		}
		return "Not matched!";
	}
}
