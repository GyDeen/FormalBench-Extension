import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PassValidity {

	public static Boolean passValidity(String p) {
		if (p.length() < 8) {
			return false;
		}
		if (p.matches(".*[a-z]+.*")) {
			if (p.matches(".*[A-Z]+.*")) {
				return true;
			}
		}
		return false;
	}
}
