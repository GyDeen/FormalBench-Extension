import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MoveNum {

	public static String moveNum(String string) {
		String move = "";
		char[] new = string.toCharArray();

		for (int index = 0; index < new.length; index++) {
			if (new[index] < '0' || new[index] > '9') {
				move += new[index];
			}
		}

		for (int index = 0; index < new.length; index++) {
			if (new[index] >= '0' && new[index] <= '9') {
				move += new[index];
			}
		}

		return move;
	}
}
