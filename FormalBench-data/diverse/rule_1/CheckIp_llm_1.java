import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckIp {

	public static String checkIp(String string) {
		String test = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$";
		if (string.matches(test)) {
			return "Valid IP address";
		} else {
			return "Invalid IP address";
		}
	}
}
