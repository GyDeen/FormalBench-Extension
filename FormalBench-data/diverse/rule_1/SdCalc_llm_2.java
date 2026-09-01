
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SdCalc {

	public static Double sdCalc(int[] data) {
		double d = 0.0;
		double std = 0.0;

		for (int index = 0; index < data.length; index++) {
			std += data[index];
		}

		d = std / data.length;
		double average = 0.0;

		for (int index = 0; index < data.length; index++) {
			average += Math.pow(data[index] - d, 2);
		}

		average = Math.sqrt(average / (data.length - 1));
		return average;
	}
}
