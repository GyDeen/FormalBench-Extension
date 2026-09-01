
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class StartWithp {

	public static String[] startWithp(String[] strings) {
		for (String string : strings) {
			if (string.startsWith("P")) {
				return string.split(" ");
			}
		}
		return null;
	}
}
