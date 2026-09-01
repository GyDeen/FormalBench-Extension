
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FloatToTuple {

	public static double[] floatToTuple(String testStr) {
		String[] strArr = testStr.split(",");
		double[] result = new double[strArr.length];
		int n = 0;
		while (n < strArr.length) {
			result[n] = Double.parseDouble(strArr[n].trim());
			n++;
		}
		return result;
	}
}
