import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsNumDecagonal {

	public static int isNumDecagonal(int num) {
		int[] arr = new int[num + 1];
		arr[0] = 0;
		for (int index = 0; index <= num - 1; index++) {
			arr[index + 1] = arr[index] + 8 * index + 1;
		}
		return arr[num];
	}
}
