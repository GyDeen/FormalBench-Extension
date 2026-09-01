import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckAlphanumeric {

	public static String checkAlphanumeric(String text) {
		return text.matches("^[a-zA-Z0-9]*$") ? "Accept" : "Discard";
	}
}
