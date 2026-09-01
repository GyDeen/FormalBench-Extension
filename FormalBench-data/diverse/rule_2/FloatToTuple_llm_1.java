
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FloatToTuple {

	public static double[] floatToTuple(String testStr) {
		String[] strArr = testStr.split(",");
		double[] result = new double[strArr.length];
		int i = 0;
		while (i < strArr.length) {
			result[i] = Double.parseDouble(strArr[i].trim());
			i++;
		}
		return result;
	}
}
