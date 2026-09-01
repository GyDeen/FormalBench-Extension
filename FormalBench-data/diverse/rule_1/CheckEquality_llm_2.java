import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckEquality {

	public static String checkEquality(String message) {
		if (message.length() == 1) {
			return "Equal";
		}
		if (message.charAt(0) == message.charAt(message.length() - 1)) {
			return "Equal";
		} else {
			return "Not Equal";
		}
	}
}
