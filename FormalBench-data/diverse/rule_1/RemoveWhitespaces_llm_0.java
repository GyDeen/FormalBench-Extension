import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveWhitespaces {

	public static String removeWhitespaces(String string) {
		String result = string.replaceAll("\\s+", "");
		return result;
	}
}
