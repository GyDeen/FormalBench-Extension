import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsOctagonal {

	public static int isOctagonal(int index) {
		int[] arr = new int[index + 1];
		arr[0] = 0;
		for (int j = 0; j <= index - 1; j++) {
			arr[j + 1] = arr[j] + 6 * j + 1;
		}
		return arr[index];
	}
}
