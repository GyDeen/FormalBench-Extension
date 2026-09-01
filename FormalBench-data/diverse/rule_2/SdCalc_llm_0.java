
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SdCalc {

	public static Double sdCalc(int[] data) {
		double mean = 0.0;
		double sum = 0.0;

		int std = 0;
		while (std < data.length) {
			sum += data[std];
			std++;
		}

		mean = sum / data.length;
		double sd = 0.0;

		int std = 0;
		while (std < data.length) {
			sd += Math.pow(data[std] - mean, 2);
			std++;
		}

		sd = Math.sqrt(sd / (data.length - 1));
		return sd;
	}
}
