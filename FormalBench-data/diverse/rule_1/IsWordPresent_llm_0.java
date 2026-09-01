import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsWordPresent {

	public static Boolean isWordPresent(String string, String text) {
		String[] tokens = string.split(" ");
		for (int n = 0; n < tokens.length; n++) {
			if (tokens[n].equals(text)) {
				return true;
			}
		}
		return false;
	}
}
