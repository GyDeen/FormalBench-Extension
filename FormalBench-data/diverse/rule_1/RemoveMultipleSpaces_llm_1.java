import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveMultipleSpaces {

	public static String removeMultipleSpaces(String str) {
		return str.replaceAll("\\s+", " ");
	}
}
