import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchWordzMiddle {

	public static String textMatchWordzMiddle(String input) {
		String empty = "Not matched!";
		if (input.contains("z")) {
			int ret = input.indexOf("z");
			if (ret != 0 && ret != input.length() - 1) {
				empty = "Found a match!";
			}
		}
		return empty;
	}
}
