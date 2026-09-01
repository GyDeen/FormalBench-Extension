
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LenLog {

	public static int lenLog(String[] array) {
		int min = Integer.MAX_VALUE;
		for (String s : array) {
			min = (s.length() < min) ? s.length() : min;
		}
		return min;
	}
}
