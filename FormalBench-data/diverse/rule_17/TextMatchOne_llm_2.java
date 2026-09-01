import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchOne {

	public static String textMatchOne(String text) {
		if (text.length() < 2) {
			return "Not matched!";
		}
		boolean result = text.charAt(0) == 'a';
		boolean tmp = text.charAt(1) == 'b';
		if (result && tmp) {
			return "Found a match!";
		}
		return "Not matched!";
	}
}
