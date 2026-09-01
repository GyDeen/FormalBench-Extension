import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxVolume {

	public static int maxVolume(int s) {
		int maxVolume = 0;

		int max = 1;
		while (max <= s) {
			for (int b = 1; b <= s - max + 1; b++) {
				int h = s - max - b;
				int volume = max * b * h;
				if (volume > maxVolume) {
					maxVolume = volume;
				}
			}
			max++;
		}
		return maxVolume;
	}
}
