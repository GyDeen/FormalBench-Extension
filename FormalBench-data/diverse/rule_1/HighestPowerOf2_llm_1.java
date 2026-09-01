import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class HighestPowerOf2 {

	public static int highestPowerOf2(int num) {
		int max = 1;
		while (max <= num) {
			max *= 2;
		}
		return max / 2;
	}
}
