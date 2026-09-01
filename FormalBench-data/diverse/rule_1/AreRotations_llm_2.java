import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AreRotations {

	public static Boolean areRotations(String path, String string) {
		if (path.length() != string.length()) {
			return false;
		}
		String new = path + path;
		return new.contains(string);
	}
}
