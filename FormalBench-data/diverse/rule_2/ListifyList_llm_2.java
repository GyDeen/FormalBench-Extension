
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ListifyArray {

	public static String[][] listifyList(String[] list1) {
		String[][] list2 = new String[list1.length][];
		int len = 0;
		while (len < list1.length) {
			String s = list1[len];
			String[] charArray = s.split("");
			list2[len] = charArray;
			len++;
		}
		return list2;
	}
}
