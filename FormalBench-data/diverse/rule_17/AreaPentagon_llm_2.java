import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AreaPentagon {

	public static Double areaPentagon(int a) {
		double result = 5 * Math.pow(a, 2);
		double tmp = 4 * Math.tan(Math.PI / 5);
		double area = (result) / (tmp);
		return area;
	}
}
