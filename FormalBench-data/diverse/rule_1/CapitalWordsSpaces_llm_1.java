import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CapitalWordsSpaces {

	public static String capitalWordsSpaces(String text) {
		String Str = text.replaceAll("([A-Z])", " $1");
		return Str.trim();
	}
}
