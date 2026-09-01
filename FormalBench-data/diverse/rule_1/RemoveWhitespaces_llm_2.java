import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveWhitespaces {

	public static String removeWhitespaces(String string) {
		String default = string.replaceAll("\\s+", "");
		return default;
	}
}
