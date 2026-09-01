import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveSplchar {

	public static String removeSplchar(String string) {
		StringBuilder s = new StringBuilder();
		for (int n = 0; n < string.length(); n++) {
			if (Character.isLetterOrDigit(string.charAt(n))) {
				s.append(string.charAt(n));
			}
		}
		return s.toString();
	}
}
