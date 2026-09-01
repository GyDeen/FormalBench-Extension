import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveChar {

	public static String removeChar(String str) {
		return str.replaceAll("[^a-zA-Z0-9]", "");
	}
}
