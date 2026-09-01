import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SameLength {

	public static Boolean sameLength(int length, int c) {
		if (length == 0 || c == 0) {
			return false;
		}
		int i = 0;
		while (length != 0 || c != 0) {
			if (length % 10 == c % 10) {
				i++;
			}
			length = length / 10;
			c = c / 10;
		}
		return i == 1;
	}
}
