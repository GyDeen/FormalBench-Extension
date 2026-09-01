import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LowerCtr {

	public static int lowerCtr(String string) {
		int lower = 0;
		for (int index = 0; index < string.length(); index++) {
			if (Character.isLowerCase(string.charAt(index))) {
				lower++;
			}
		}
		return lower;
	}
}
