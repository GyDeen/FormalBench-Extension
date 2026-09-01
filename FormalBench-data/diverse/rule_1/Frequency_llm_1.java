
import java.io.*;
import java.lang.*;

class Frequency {

	public static int frequency(int[] data, int b) {
		int f = 0;
		for (int index = 0; index < data.length; index++) {
			if (data[index] == b) {
				f++;
			}
		}
		return f;
	}
}
