import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckChar {

	public static String checkChar(String str) {
		if (str.length() == 1) {
			return "Valid";
		}
		return str.matches("^([a-zA-Z]).*\\1$") ? "Valid" : "Invalid";
	}
}
