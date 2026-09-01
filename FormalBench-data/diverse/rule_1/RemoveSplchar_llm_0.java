import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveSplchar {

	public static String removeSplchar(String string) {
		StringBuilder builder = new StringBuilder();
		for (int n = 0; n < string.length(); n++) {
			if (Character.isLetterOrDigit(string.charAt(n))) {
				builder.append(string.charAt(n));
			}
		}
		return builder.toString();
	}
}
