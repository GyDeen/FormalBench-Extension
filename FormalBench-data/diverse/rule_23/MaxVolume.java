import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxVolume {

	public static int maxVolume(int s) {
		int maxVolume = 0;

		for (int l = 1; s >= l; l++) {
			for (int b = 1; s - l + 1 >= b; b++) {
				int h = s - l - b;
				int volume = l * b * h;
				if (maxVolume < volume) {
					maxVolume = volume;
				}
			}
		}
		return maxVolume;
	}
}
