
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SdCalc {

	public static Double sdCalc(int[] data) {
		double mean = 0.0;
		double sum = 0.0;

		int index = 0;
		while (index < data.length) {
			sum += data[index];
			index++;
		}

		mean = sum / data.length;
		double sd = 0.0;

		int index = 0;
		while (index < data.length) {
			sd += Math.pow(data[index] - mean, 2);
			index++;
		}

		sd = Math.sqrt(sd / (data.length - 1));
		return sd;
	}
}
