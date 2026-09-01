import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AreaPentagon {

	public static Double areaPentagon(int n) {
		double result = (5 * Math.pow(n, 2)) / (4 * Math.tan(Math.PI / 5));
		return result;
	}
}
