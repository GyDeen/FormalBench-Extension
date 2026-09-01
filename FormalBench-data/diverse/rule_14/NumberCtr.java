import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NumberCtr {

	public static int numberCtr(String str) {
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (!(Character.isDigit(str.charAt(i))))
				;
			else {
				count++;
			}
		}
		return count;
	}
}
