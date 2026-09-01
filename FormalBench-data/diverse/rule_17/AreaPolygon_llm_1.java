import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AreaPolygon {

	public static Double areaPolygon(int s, int l) {
		int area = s * (l * l);
		double temp = 4 * Math.tan(Math.PI / s);
		return area / (temp);
	}
}
