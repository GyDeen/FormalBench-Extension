import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PassValidity {

	public static Boolean passValidity(String input) {
		if (input.length() < 8) {
			return false;
		}
		if (input.matches(".*[a-z]+.*") && input.matches(".*[A-Z]+.*")
				&& input.matches(".*[0-9]+.*")) {
			return true;
		}
		return false;
	}
}
