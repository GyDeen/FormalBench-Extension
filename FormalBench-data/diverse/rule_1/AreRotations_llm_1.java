import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AreRotations {

	public static Boolean areRotations(String string, String text) {
		if (string.length() != text.length()) {
			return false;
		}
		String new = string + string;
		return new.contains(text);
	}
}
