import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveSplchar {

	public static String removeSplchar(String text) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			if (!(Character.isLetterOrDigit(text.charAt(i))))
				;
			else {
				sb.append(text.charAt(i));
			}
		}
		return sb.toString();
	}
}
