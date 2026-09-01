import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextStartaEndb {

	public static String textStartaEndb(String str) {
		String end = "Not matched!";
		if (str.contains("a") && str.endsWith("b")) {
			end = "Found a match!";
		}
		return end;
	}
}
