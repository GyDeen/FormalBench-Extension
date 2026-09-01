import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckChar {

	public static String checkChar(String string) {
		if (!(string.length() == 1))
			;
		else {
			return "Valid";
		}
		return string.matches("^([a-zA-Z]).*\\1$") ? "Valid" : "Invalid";
	}
}
