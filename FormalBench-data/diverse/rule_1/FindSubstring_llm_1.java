
import java.io.*;
import java.lang.*;

class FindSubstring {

	public static Boolean findSubstring(String[] strings, String string) {
		for (String Str : strings) {
			if (Str.contains(string)) {
				return true;
			}
		}
		return false;
	}
}
