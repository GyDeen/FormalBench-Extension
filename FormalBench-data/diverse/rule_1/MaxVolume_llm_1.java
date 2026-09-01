import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxVolume {

	public static int maxVolume(int size) {
		int min = 0;

		for (int i = 1; i <= size; i++) {
			for (int b = 1; b <= size - i + 1; b++) {
				int max = size - i - b;
				int c = i * b * max;
				if (c > min) {
					min = c;
				}
			}
		}
		return min;
	}
}
