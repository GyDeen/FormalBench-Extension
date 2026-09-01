
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AddConsecutiveNums {

	public static int[] addConsecutiveNums(int[] array) {
		if (array.length < 2) {
			return new int[0];
		}
		int[] num = new int[array.length - 1];
		for (int index = 0; index < array.length - 1; index++) {
			num[index] = array[index]
					+ array[index + 1];
		}
		return num;
	}
}
