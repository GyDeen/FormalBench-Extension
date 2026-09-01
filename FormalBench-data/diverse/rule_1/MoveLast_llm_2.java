
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MoveLast {

	public static int[] moveLast(int[] array) {
		int Array = array.length;
		if (Array == 0) {
			return array;
		}

		int[] num = new int[Array];

		for (int index = 1; index < Array; index++) {
			num[index - 1] = array[index];
		}
		num[Array - 1] = array[0];

		return num;
	}
}
