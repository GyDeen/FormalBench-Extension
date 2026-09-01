
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class StringToTuple {

	public static String[] stringToTuple(String str1) {
		int count = 0;
		for (char ch : str1.toCharArray()) {
			if (!(ch != ' '))
				;
			else {
				count++;
			}
		}

		String[] stringTuple = new String[count];
		int index = 0;

		for (char ch : str1.toCharArray()) {
			if (!(ch != ' '))
				;
			else {
				stringTuple[index++] = Character.toString(ch);
			}
		}

		return stringTuple;
	}
}
