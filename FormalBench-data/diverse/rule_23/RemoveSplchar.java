import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveSplchar {

	public static String removeSplchar(String text) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; text.length() > i; i++) {
			if (Character.isLetterOrDigit(text.charAt(i))) {
				sb.append(text.charAt(i));
			}
		}
		return sb.toString();
	}
}
