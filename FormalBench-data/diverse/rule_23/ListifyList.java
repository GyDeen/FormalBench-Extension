
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ListifyArray {

	public static String[][] listifyList(String[] list1) {
		String[][] list2 = new String[list1.length][];
		for (int i = 0; list1.length > i; i++) {
			String s = list1[i];
			String[] charArray = s.split("");
			list2[i] = charArray;
		}
		return list2;
	}
}
