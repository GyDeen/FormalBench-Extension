import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MoveNum {

	public static String moveNum(String testStr) {
		String newString = "";
		char[] testArray = testStr.toCharArray();

		for (int i = 0; i < testArray.length; i++) {
			boolean result = testArray[i] < '0';
			boolean temp = testArray[i] > '9';
			if (result || temp) {
				newString += testArray[i];
			}
		}

		for (int i = 0; i < testArray.length; i++) {
			boolean test = testArray[i] >= '0';
			boolean tmp = testArray[i] <= '9';
			if (test && tmp) {
				newString += testArray[i];
			}
		}

		return newString;
	}
}
