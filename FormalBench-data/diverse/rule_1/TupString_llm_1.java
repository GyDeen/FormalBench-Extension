
import java.io.*;
import java.lang.*;

class TupString {

	public static String tupString(String[] args) {
		StringBuilder string = new StringBuilder();
		for (String t : args) {
			string.append(t);
		}
		return string.toString();
	}
}
