import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchWordz {

	public static String textMatchWordz(String text) {
		String result = "Not matched!";
		result = (text.contains("z")) ? "Found a match!" : result;
		return result;
	}
}
