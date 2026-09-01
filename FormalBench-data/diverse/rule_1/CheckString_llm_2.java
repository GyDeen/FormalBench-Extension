import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckString {

	public static Boolean checkString(String text) {
		if (text.matches("[a-zA-Z]+[0-9]+")) {
			return true;
		}
		return false;
	}
}
