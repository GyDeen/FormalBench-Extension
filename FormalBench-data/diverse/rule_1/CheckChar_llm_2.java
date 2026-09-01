import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckChar {

	public static String checkChar(String text) {
		if (text.length() == 1) {
			return "Valid";
		}
		return text.matches("^([a-zA-Z]).*\\1$") ? "Valid" : "Invalid";
	}
}
