
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ListifyArray {

	public static String[][] listifyList(String[] list1) {
		String[][] list2 = new String[list1.length][];
		int index = 0;
		while (index < list1.length) {
			String s = list1[index];
			String[] charArray = s.split("");
			list2[index] = charArray;
			index++;
		}
		return list2;
	}
}
