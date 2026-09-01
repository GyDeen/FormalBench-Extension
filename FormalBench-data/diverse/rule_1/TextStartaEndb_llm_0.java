import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextStartaEndb {

	public static String textStartaEndb(String string) {
		String end = "Not matched!";
		if (string.contains("a") && string.endsWith("b")) {
			end = "Found a match!";
		}
		return end;
	}
}
