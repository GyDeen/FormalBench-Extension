
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FloatToTuple {

	public static double[] floatToTuple(String string) {
		String[] test = string.split(",");
		double[] ret = new double[test.length];
		for (int n = 0; n < test.length; n++) {
			ret[n] = Double.parseDouble(test[n].trim());
		}
		return ret;
	}
}
