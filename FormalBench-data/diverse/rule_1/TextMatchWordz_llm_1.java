import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchWordz {

	public static String textMatchWordz(String input) {
		String ret = "Not matched!";
		if (input.contains("z")) {
			ret = "Found a match!";
		}
		return ret;
	}
}
