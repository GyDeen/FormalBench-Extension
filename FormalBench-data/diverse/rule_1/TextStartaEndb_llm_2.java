import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextStartaEndb {

	public static String textStartaEndb(String string) {
		String output = "Not matched!";
		if (string.contains("a") && string.endsWith("b")) {
			output = "Found a match!";
		}
		return output;
	}
}
