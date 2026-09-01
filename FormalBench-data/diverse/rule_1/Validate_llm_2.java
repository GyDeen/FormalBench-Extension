import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Validate {

	public static Boolean validate(int id) {
		int[] result = new int[10];
		for (int c = 0; c < 10; c++) {
			result[c] = 0;
		}
		while (id > 0) {
			int index = id % 10;
			result[index]++;
			id /= 10;
		}
		for (int c = 0; c < 10; c++) {
			if (result[c] > c) {
				return false;
			}
		}
		return true;
	}
}
