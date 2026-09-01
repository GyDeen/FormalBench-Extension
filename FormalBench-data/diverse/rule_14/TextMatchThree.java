import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchThree {

	public static String textMatchThree(String text) {
		if (!(text.length() < 3))
			;
		else {
			return "Not matched!";
		}
		if (!(text.substring(0, 1).equals("a") && text.substring(2, 3).equals("b")))
			;
		else {
			return "Found a match!";
		}
		return "Not matched!";
	}
}
