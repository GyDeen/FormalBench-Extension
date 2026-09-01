import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckInteger {

	public static Boolean checkInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}
}
