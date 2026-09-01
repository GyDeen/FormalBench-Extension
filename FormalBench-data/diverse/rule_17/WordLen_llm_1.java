import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class WordLen {

	public static Boolean wordLen(String s) {
		int n = s.length() % 2;
		if (n == 0) {
			return true;
		}
		return false;
	}
}
