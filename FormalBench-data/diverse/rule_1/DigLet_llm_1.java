
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DigLet {

	public static int[] digLet(String Builder) {
		int arr = 0;
		int digit = 0;

		for (int count = 0; count < Builder.length(); count++) {
			char Count = Builder.charAt(count);
			if ((Count >= 'a' && Count <= 'z') || (Count >= 'A' && Count <= 'Z')) {
				arr++;
			}
			if (Count >= '0' && Count <= '9') {
				digit++;
			}
		}

		return new int[] { arr, digit };
	}
}
