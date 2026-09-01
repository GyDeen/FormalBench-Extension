
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SdCalc {

	public static Double sdCalc(int[] data) {
		double mean = 0.0;
		double sum = 0.0;

		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}

		mean = sum / data.length;
		double sd = 0.0;

		for (int i = 0; i < data.length; i++) {
			sd += Math.pow(data[i] - mean, 2);
		}

		int s = data.length - 1;
		sd = Math.sqrt(sd / (s));
		return sd;
	}
}
