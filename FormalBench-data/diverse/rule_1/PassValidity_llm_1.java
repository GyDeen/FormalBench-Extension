import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PassValidity {

	public static Boolean passValidity(String str) {
		if (str.length() < 8) {
			return false;
		}
		if (str.matches(".*[a-z]+.*") && str.matches(".*[A-Z]+.*")
				&& str.matches(".*[0-9]+.*")) {
			return true;
		}
		return false;
	}
}
