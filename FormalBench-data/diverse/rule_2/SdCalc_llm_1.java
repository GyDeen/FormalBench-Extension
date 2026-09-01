
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SdCalc {

	public static Double sdCalc(int[] data) {
		double mean = 0.0;
		double sum = 0.0;

		int n = 0;
		while (n < data.length) {
			sum += data[n];
			n++;
		}

		mean = sum / data.length;
		double sd = 0.0;

		int n = 0;
		while (n < data.length) {
			sd += Math.pow(data[n] - mean, 2);
			n++;
		}

		sd = Math.sqrt(sd / (data.length - 1));
		return sd;
	}
}
