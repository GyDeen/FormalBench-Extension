import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveMultipleSpaces {

	public static String removeMultipleSpaces(String text) {
		return text.replaceAll("\\s+", " ");
	}
}
