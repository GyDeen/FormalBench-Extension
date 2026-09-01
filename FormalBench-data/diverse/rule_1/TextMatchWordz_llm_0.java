import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextMatchWordz {

	public static String textMatchWordz(String string) {
		String ret = "Not matched!";
		if (string.contains("z")) {
			ret = "Found a match!";
		}
		return ret;
	}
}
