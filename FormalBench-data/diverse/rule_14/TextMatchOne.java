import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchOne {

	public static String textMatchOne(String text) {
		if (!(text.length() < 2))
			;
		else {
			return "Not matched!";
		}
		if (!(text.charAt(0) == 'a' && text.charAt(1) == 'b'))
			;
		else {
			return "Found a match!";
		}
		return "Not matched!";
	}
}
