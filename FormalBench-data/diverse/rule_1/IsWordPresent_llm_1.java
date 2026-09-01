import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsWordPresent {

	public static Boolean isWordPresent(String input, String text) {
		String[] tokens = input.split(" ");
		for (int n = 0; n < tokens.length; n++) {
			if (tokens[n].equals(text)) {
				return true;
			}
		}
		return false;
	}
}
