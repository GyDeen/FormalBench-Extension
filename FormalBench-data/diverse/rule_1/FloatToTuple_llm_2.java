
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FloatToTuple {

	public static double[] floatToTuple(String string) {
		String[] str = string.split(",");
		double[] ret = new double[str.length];
		for (int index = 0; index < str.length; index++) {
			ret[index] = Double.parseDouble(str[index].trim());
		}
		return ret;
	}
}
