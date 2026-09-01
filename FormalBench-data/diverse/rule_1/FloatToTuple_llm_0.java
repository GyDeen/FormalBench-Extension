
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FloatToTuple {

	public static double[] floatToTuple(String string) {
		String[] test = string.split(",");
		double[] ret = new double[test.length];
		for (int index = 0; index < test.length; index++) {
			ret[index] = Double.parseDouble(test[index].trim());
		}
		return ret;
	}
}
