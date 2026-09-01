import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Validate {

	public static Boolean validate(int id) {
		int[] result = new int[10];
		for (int r = 0; r < 10; r++) {
			result[r] = 0;
		}
		while (id > 0) {
			int index = id % 10;
			result[index]++;
			id /= 10;
		}
		for (int r = 0; r < 10; r++) {
			if (result[r] > r) {
				return false;
			}
		}
		return true;
	}
}
