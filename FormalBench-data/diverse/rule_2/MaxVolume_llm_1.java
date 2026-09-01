import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxVolume {

	public static int maxVolume(int s) {
		int maxVolume = 0;

		int min = 1;
		while (min <= s) {
			for (int b = 1; b <= s - min + 1; b++) {
				int h = s - min - b;
				int volume = min * b * h;
				if (volume > maxVolume) {
					maxVolume = volume;
				}
			}
			min++;
		}
		return maxVolume;
	}
}
