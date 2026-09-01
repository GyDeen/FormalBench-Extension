import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveWhitespaces {

	public static String removeWhitespaces(String input) {
		String result = input.replaceAll("\\s+", "");
		return result;
	}
}
