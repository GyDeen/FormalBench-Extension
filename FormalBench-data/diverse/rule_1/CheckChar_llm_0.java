import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckChar {

	public static String checkChar(String input) {
		if (input.length() == 1) {
			return "Valid";
		}
		return input.matches("^([a-zA-Z]).*\\1$") ? "Valid" : "Invalid";
	}
}
