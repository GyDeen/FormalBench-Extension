
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SdCalc {

	public static Double sdCalc(int[] data) {
		double d = 0.0;
		double variance = 0.0;

		for (int index = 0; index < data.length; index++) {
			variance += data[index];
		}

		d = variance / data.length;
		double std = 0.0;

		for (int index = 0; index < data.length; index++) {
			std += Math.pow(data[index] - d, 2);
		}

		std = Math.sqrt(std / (data.length - 1));
		return std;
	}
}
