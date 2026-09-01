import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckAlphanumeric {

	public static String checkAlphanumeric(String str) {
		return str.matches("^[a-zA-Z0-9]*$") ? "Accept" : "Discard";
	}
}
