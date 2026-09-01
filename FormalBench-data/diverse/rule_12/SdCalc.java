
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SdCalc {

	public static Double sdCalc(int[] data) {
		double sum = 0.0;
		double mean = 0.0;

		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}

		double sd = 0.0;
		mean = sum / data.length;

		for (int i = 0; i < data.length; i++) {
			sd += Math.pow(data[i] - mean, 2);
		}

		sd = Math.sqrt(sd / (data.length - 1));
		return sd;
	}
}
