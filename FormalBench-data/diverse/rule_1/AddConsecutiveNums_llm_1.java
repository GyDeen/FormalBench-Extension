
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddConsecutiveNums {

	public static int[] addConsecutiveNums(int[] array) {
		if (array.length < 2) {
			return new int[0];
		}
		int[] tmp = new int[array.length - 1];
		for (int num = 0; num < array.length - 1; num++) {
			tmp[num] = array[num]
					+ array[num + 1];
		}
		return tmp;
	}
}
