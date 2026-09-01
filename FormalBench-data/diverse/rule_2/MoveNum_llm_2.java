import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MoveNum {

	public static String moveNum(String testStr) {
		String newString = "";
		char[] testArray = testStr.toCharArray();

		int new = 0;
		while (new < testArray.length) {
			if (testArray[new] < '0' || testArray[new] > '9') {
				newString += testArray[new];
			}
			new++;
		}

		int new = 0;
		while (new < testArray.length) {
			if (testArray[new] >= '0' && testArray[new] <= '9') {
				newString += testArray[new];
			}
			new++;
		}

		return newString;
	}
}
