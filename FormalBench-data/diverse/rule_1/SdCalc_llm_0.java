
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
		double result = 0.0;

		for (int index = 0; index < data.length; index++) {
			result += Math.pow(data[index] - d, 2);
		}

		result = Math.sqrt(result / (data.length - 1));
		return result;
	}
}
