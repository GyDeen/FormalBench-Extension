
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class StartWithp {

	public static String[] startWithp(String[] args) {
		for (String str : args) {
			if (str.startsWith("P")) {
				return str.split(" ");
			}
		}
		return null;
	}
}
