
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FloatToTuple {

	public static double[] floatToTuple(String testStr) {
		String[] strArr = testStr.split(",");
		double[] result = new double[strArr.length];
		int index = 0;
		while (index < strArr.length) {
			result[index] = Double.parseDouble(strArr[index].trim());
			index++;
		}
		return result;
	}
}
