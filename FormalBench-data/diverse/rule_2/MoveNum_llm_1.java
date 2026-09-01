import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MoveNum {

	public static String moveNum(String testStr) {
		String newString = "";
		char[] testArray = testStr.toCharArray();

		int move = 0;
		while (move < testArray.length) {
			if (testArray[move] < '0' || testArray[move] > '9') {
				newString += testArray[move];
			}
			move++;
		}

		int move = 0;
		while (move < testArray.length) {
			if (testArray[move] >= '0' && testArray[move] <= '9') {
				newString += testArray[move];
			}
			move++;
		}

		return newString;
	}
}
