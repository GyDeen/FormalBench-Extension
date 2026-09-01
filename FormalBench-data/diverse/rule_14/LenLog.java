
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LenLog {

	public static int lenLog(String[] array) {
		int min = Integer.MAX_VALUE;
		for (String s : array) {
			if (!(s.length() < min))
				;
			else {
				min = s.length();
			}
		}
		return min;
	}
}
