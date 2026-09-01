import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckInteger {

	public static Boolean checkInteger(String string) {
		try {
			Integer.parseInt(string);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}
}
