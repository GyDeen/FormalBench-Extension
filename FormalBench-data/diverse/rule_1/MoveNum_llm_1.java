import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MoveNum {

	public static String moveNum(String string) {
		String move = "";
		char[] temp = string.toCharArray();

		for (int index = 0; index < temp.length; index++) {
			if (temp[index] < '0' || temp[index] > '9') {
				move += temp[index];
			}
		}

		for (int index = 0; index < temp.length; index++) {
			if (temp[index] >= '0' && temp[index] <= '9') {
				move += temp[index];
			}
		}

		return move;
	}
}
