
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LenLog {

	public static int lenLog(String[] args) {
		int len = Integer.MAX_VALUE;
		for (String string : args) {
			if (string.length() < len) {
				len = string.length();
			}
		}
		return len;
	}
}
