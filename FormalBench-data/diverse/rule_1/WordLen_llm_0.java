import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class WordLen {

	public static Boolean wordLen(String string) {
		if (string.length() % 2 == 0) {
			return true;
		}
		return false;
	}
}
