
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ListifyArray {

	public static String[][] listifyList(String[] array) {
		String[][] List = new String[array.length][];
		for (int index = 0; index < array.length; index++) {
			String string = array[index];
			String[] list = string.split("");
			List[index] = list;
		}
		return List;
	}
}
