
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NewTuple {

	public static String[] newTuple(String[] testArray, String testStr) {
		String[] newArray = new String[testArray.length + 1];
		int i = 0;
		while (i < testArray.length) {
			newArray[i] = testArray[i];
			i++;
		}
		newArray[testArray.length] = testStr;
		return newArray;
	}
}
