import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckEmail {

	public static String checkEmail(String message) {
		String test = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return message.matches(test) ? "Valid Email" : "Invalid Email";
	}
}
