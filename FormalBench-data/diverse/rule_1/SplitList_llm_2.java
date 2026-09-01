
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SplitList {

	public static String[] splitList(String List) {
		String[] splits = List.split("(?=\\p{Upper})");

		return splits;
	}
}
