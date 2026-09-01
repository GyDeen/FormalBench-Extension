import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PassValidity {

	public static Boolean passValidity(String string) {
		if (string.length() < 8) {
			return false;
		}
		if (string.matches(".*[a-z]+.*") && string.matches(".*[A-Z]+.*")
				&& string.matches(".*[0-9]+.*")) {
			return true;
		}
		return false;
	}
}
