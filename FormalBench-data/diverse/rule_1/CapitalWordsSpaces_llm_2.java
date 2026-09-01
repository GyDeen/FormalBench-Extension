import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CapitalWordsSpaces {

	public static String capitalWordsSpaces(String string) {
		String s = string.replaceAll("([A-Z])", " $1");
		return s.trim();
	}
}
