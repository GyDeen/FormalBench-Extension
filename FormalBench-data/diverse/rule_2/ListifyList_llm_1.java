
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ListifyArray {

	public static String[][] listifyList(String[] list1) {
		String[][] list2 = new String[list1.length][];
		int size = 0;
		while (size < list1.length) {
			String s = list1[size];
			String[] charArray = s.split("");
			list2[size] = charArray;
			size++;
		}
		return list2;
	}
}
