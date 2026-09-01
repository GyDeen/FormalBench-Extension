import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MoveNum {

	public static String moveNum(String string) {
		String test = "";
		char[] new = string.toCharArray();

		for (int index = 0; index < new.length; index++) {
			if (new[index] < '0' || new[index] > '9') {
				test += new[index];
			}
		}

		for (int index = 0; index < new.length; index++) {
			if (new[index] >= '0' && new[index] <= '9') {
				test += new[index];
			}
		}

		return test;
	}
}
