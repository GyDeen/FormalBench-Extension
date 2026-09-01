import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveChar {

	public static String removeChar(String string) {
		return string.replaceAll("[^a-zA-Z0-9]", "");
	}
}
