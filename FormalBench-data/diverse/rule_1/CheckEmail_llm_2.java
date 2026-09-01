import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckEmail {

	public static String checkEmail(String msg) {
		String check = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return msg.matches(check) ? "Valid Email" : "Invalid Email";
	}
}
