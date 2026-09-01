
import java.io.*;
import java.lang.*;

class Frequency {

	public static int frequency(int[] arr, int b) {
		int f = 0;
		for (int index = 0; index < arr.length; index++) {
			if (arr[index] == b) {
				f++;
			}
		}
		return f;
	}
}
