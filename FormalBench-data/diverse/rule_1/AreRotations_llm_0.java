import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AreRotations {

	public static Boolean areRotations(String path, String text) {
		if (path.length() != text.length()) {
			return false;
		}
		String string = path + path;
		return string.contains(text);
	}
}
