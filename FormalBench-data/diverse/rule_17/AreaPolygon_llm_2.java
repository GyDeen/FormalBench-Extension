import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AreaPolygon {

	public static Double areaPolygon(int s, int l) {
		int r = s * (l * l);
		double tmp = 4 * Math.tan(Math.PI / s);
		return r / (tmp);
	}
}
