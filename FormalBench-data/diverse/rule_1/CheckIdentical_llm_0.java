
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckIdentical {

	public static Boolean checkIdentical(int[][] array, int[][] test) {
		if (array.length != test.length) {
			return false;
		}
		for (int index = 0; index < array.length; index++) {
			if (array[index].length != test[index].length) {
				return false;
			}
			for (int n = 0; n < array[index].length; n++) {
				if (array[index][n] != test[index][n]) {
					return false;
				}
			}
		}
		return true;
	}
}
