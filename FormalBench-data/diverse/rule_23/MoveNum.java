import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MoveNum {

	public static String moveNum(String testStr) {
		String newString = "";
		char[] testArray = testStr.toCharArray();

		for (int i = 0; testArray.length > i; i++) {
			if ('0' > testArray[i] || '9' < testArray[i]) {
				newString += testArray[i];
			}
		}

		for (int i = 0; testArray.length > i; i++) {
			if ('0' <= testArray[i] && '9' >= testArray[i]) {
				newString += testArray[i];
			}
		}

		return newString;
	}
}
