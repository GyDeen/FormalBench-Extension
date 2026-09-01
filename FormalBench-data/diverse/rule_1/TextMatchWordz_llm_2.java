import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchWordz {

	public static String textMatchWordz(String string) {
		String match = "Not matched!";
		if (string.contains("z")) {
			match = "Found a match!";
		}
		return match;
	}
}
