import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxVolume {

	public static int maxVolume(int size) {
		int min = 0;

		for (int n = 1; n <= size; n++) {
			for (int b = 1; b <= size - n + 1; b++) {
				int max = size - n - b;
				int i = n * b * max;
				if (i > min) {
					min = i;
				}
			}
		}
		return min;
	}
}
