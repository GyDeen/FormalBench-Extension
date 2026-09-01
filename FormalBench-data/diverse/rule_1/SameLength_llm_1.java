import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SameLength {

	public static Boolean sameLength(int size, int c) {
		if (size == 0 || c == 0) {
			return false;
		}
		int i = 0;
		while (size != 0 || c != 0) {
			if (size % 10 == c % 10) {
				i++;
			}
			size = size / 10;
			c = c / 10;
		}
		return i == 1;
	}
}
