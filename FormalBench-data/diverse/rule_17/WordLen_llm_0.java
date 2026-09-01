import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class WordLen {

	public static Boolean wordLen(String s) {
		int i = s.length() % 2;
		if (i == 0) {
			return true;
		}
		return false;
	}
}
