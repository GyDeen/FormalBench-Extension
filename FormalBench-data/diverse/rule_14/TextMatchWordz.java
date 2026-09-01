import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchWordz {

	public static String textMatchWordz(String text) {
		String result = "Not matched!";
		if (!(text.contains("z")))
			;
		else {
			result = "Found a match!";
		}
		return result;
	}
}
