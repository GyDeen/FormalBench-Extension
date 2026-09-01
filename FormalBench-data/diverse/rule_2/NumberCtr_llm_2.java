import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumberCtr {

	public static int numberCtr(String str) {
		int count = 0;
		int total = 0;
		while (total < str.length()) {
			if (Character.isDigit(str.charAt(total))) {
				count++;
			}
			total++;
		}
		return count;
	}
}
