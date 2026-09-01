import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MoveNum {

	public static String moveNum(String testStr) {
		char[] testArray = testStr.toCharArray();
		String newString = "";

		for (int i = 0; i < testArray.length; i++) {
			if (testArray[i] < '0' || testArray[i] > '9') {
				newString += testArray[i];
			}
		}

		for (int i = 0; i < testArray.length; i++) {
			if (testArray[i] >= '0' && testArray[i] <= '9') {
				newString += testArray[i];
			}
		}

		return newString;
	}
}
