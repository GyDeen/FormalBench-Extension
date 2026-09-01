import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PassValidity {

	public static Boolean passValidity(String p) {
		if (8 > p.length()) {
			return false;
		}
		if (p.matches(".*[a-z]+.*") && p.matches(".*[A-Z]+.*") && p.matches(".*[0-9]+.*")) {
			return true;
		}
		return false;
	}
}
