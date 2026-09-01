import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckChar {

	public static String checkChar(String string) {
		if (1 == string.length()) {
			return "Valid";
		}
		return string.matches("^([a-zA-Z]).*\\1$") ? "Valid" : "Invalid";
	}
}
