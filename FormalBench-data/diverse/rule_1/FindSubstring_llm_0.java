
import java.io.*;
import java.lang.*;

class FindSubstring {

	public static Boolean findSubstring(String[] strings, String array) {
		for (String string : strings) {
			if (string.contains(array)) {
				return true;
			}
		}
		return false;
	}
}
