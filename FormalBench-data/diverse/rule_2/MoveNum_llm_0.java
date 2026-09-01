import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MoveNum {

	public static String moveNum(String testStr) {
		String newString = "";
		char[] testArray = testStr.toCharArray();

		int result = 0;
		while (result < testArray.length) {
			if (testArray[result] < '0' || testArray[result] > '9') {
				newString += testArray[result];
			}
			result++;
		}

		int result = 0;
		while (result < testArray.length) {
			if (testArray[result] >= '0' && testArray[result] <= '9') {
				newString += testArray[result];
			}
			result++;
		}

		return newString;
	}
}
