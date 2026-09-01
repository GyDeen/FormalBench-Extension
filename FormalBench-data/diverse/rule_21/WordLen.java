import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class WordLen {

	public static Boolean wordLen(String s) {
		if (0 == s.length() % 2) {
			return true;
		}
		return false;
	}
}
