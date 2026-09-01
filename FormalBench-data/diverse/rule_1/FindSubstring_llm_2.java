
import java.io.*;
import java.lang.*;

class FindSubstring {

	public static Boolean findSubstring(String[] string, String array) {
		for (String Str : string) {
			if (Str.contains(array)) {
				return true;
			}
		}
		return false;
	}
}
