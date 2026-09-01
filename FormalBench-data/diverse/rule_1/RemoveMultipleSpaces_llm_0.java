import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveMultipleSpaces {

	public static String removeMultipleSpaces(String string) {
		return string.replaceAll("\\s+", " ");
	}
}
