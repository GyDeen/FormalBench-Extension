
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class StartWithp {

	public static String[] startWithp(String[] words) {
		for (String w : words) {
			if (!(w.startsWith("P")))
				;
			else {
				return w.split(" ");
			}
		}
		return null;
	}
}
