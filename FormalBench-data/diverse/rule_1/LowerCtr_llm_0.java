import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LowerCtr {

	public static int lowerCtr(String string) {
		int lower = 0;
		for (int c = 0; c < string.length(); c++) {
			if (Character.isLowerCase(string.charAt(c))) {
				lower++;
			}
		}
		return lower;
	}
}
