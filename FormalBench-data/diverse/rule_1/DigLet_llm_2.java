
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DigLet {

	public static int[] digLet(String string) {
		int Array = 0;
		int digit = 0;

		for (int count = 0; count < string.length(); count++) {
			char Count = string.charAt(count);
			if ((Count >= 'a' && Count <= 'z') || (Count >= 'A' && Count <= 'Z')) {
				Array++;
			}
			if (Count >= '0' && Count <= '9') {
				digit++;
			}
		}

		return new int[] { Array, digit };
	}
}
