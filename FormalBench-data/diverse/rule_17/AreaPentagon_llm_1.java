import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AreaPentagon {

	public static Double areaPentagon(int a) {
		double b = 5 * Math.pow(a, 2);
		double temp = 4 * Math.tan(Math.PI / 5);
		double area = (b) / (temp);
		return area;
	}
}
