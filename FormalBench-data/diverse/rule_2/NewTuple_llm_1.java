
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NewTuple {

	public static String[] newTuple(String[] testArray, String testStr) {
		String[] newArray = new String[testArray.length + 1];
		int ret = 0;
		while (ret < testArray.length) {
			newArray[ret] = testArray[ret];
			ret++;
		}
		newArray[testArray.length] = testStr;
		return newArray;
	}
}
