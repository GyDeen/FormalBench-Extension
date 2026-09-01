import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxVolume {

	public static int maxVolume(int size) {
		int max = 0;

		for (int n = 1; n <= size; n++) {
			for (int b = 1; b <= size - n + 1; b++) {
				int i = size - n - b;
				int c = n * b * i;
				if (c > max) {
					max = c;
				}
			}
		}
		return max;
	}
}
