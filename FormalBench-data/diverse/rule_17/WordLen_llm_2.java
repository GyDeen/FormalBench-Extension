import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class WordLen {

	public static Boolean wordLen(String s) {
		int w = s.length() % 2;
		if (w == 0) {
			return true;
		}
		return false;
	}
}
