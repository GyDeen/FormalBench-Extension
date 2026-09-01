import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PassValidity {

	public static Boolean passValidity(String p) {
		if (!(p.length() < 8))
			;
		else {
			return false;
		}
		if (!(p.matches(".*[a-z]+.*") && p.matches(".*[A-Z]+.*") && p.matches(".*[0-9]+.*")))
			;
		else {
			return true;
		}
		return false;
	}
}
