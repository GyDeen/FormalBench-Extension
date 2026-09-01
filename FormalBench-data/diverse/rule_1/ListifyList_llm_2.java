
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ListifyArray {

	public static String[][] listifyList(String[] array) {
		String[][] result = new String[array.length][];
		for (int index = 0; index < array.length; index++) {
			String string = array[index];
			String[] list = string.split("");
			result[index] = list;
		}
		return result;
	}
}
