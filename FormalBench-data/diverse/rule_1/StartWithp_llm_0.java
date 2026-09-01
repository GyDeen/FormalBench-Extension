
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class StartWithp {

	public static String[] startWithp(String[] args) {
		for (String string : args) {
			if (string.startsWith("P")) {
				return string.split(" ");
			}
		}
		return null;
	}
}
