import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsWordPresent {

	public static Boolean isWordPresent(String string, String text) {
		String[] strings = string.split(" ");
		for (int n = 0; n < strings.length; n++) {
			if (strings[n].equals(text)) {
				return true;
			}
		}
		return false;
	}
}
