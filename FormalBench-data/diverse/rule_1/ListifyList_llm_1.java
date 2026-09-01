
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ListifyArray {

	public static String[][] listifyList(String[] array) {
		String[][] list = new String[array.length][];
		for (int index = 0; index < array.length; index++) {
			String string = array[index];
			String[] str = string.split("");
			list[index] = str;
		}
		return list;
	}
}
