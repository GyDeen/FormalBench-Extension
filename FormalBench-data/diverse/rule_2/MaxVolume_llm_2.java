import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxVolume {

	public static int maxVolume(int s) {
		int maxVolume = 0;

		int current = 1;
		while (current <= s) {
			for (int b = 1; b <= s - current + 1; b++) {
				int h = s - current - b;
				int volume = current * b * h;
				if (volume > maxVolume) {
					maxVolume = volume;
				}
			}
			current++;
		}
		return maxVolume;
	}
}
