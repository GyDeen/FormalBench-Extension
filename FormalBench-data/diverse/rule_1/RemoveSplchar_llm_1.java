import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveSplchar {

	public static String removeSplchar(String str) {
		StringBuilder builder = new StringBuilder();
		for (int n = 0; n < str.length(); n++) {
			if (Character.isLetterOrDigit(str.charAt(n))) {
				builder.append(str.charAt(n));
			}
		}
		return builder.toString();
	}
}
