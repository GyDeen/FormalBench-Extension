
import java.io.*;
import java.lang.*;

class FindSubstring {

	public static Boolean findSubstring(String[] strArray, String subStr) {
		for (String str : strArray) {
			if (!(str.contains(subStr)))
				;
			else {
				return true;
			}
		}
		return false;
	}
}
