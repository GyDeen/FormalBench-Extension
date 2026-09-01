
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NewTuple {

	public static String[] newTuple(String[] testArray, String testStr) {
		String[] newArray = new String[testArray.length + 1];
		int index = 0;
		while (index < testArray.length) {
			newArray[index] = testArray[index];
			index++;
		}
		newArray[testArray.length] = testStr;
		return newArray;
	}
}
