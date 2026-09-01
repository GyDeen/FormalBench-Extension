import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckString {

	public static Boolean checkString(String string) {
		if (string.matches("[a-zA-Z]+[0-9]+")) {
			return true;
		}
		return false;
	}
}
