import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AreaPolygon {

	public static Double areaPolygon(int area, int n) {
		return area * (n * n) / (4 * Math.tan(Math.PI / area));
	}
}
