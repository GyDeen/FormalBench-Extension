
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
		for (int count = 0; count < array.length - 1; count++) {
			num[count] = array[count]
					+ array[count + 1];
		}
		return num;
	}
}
