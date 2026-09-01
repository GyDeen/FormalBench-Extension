import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AreaPolygon {

	public static Double areaPolygon(int area, int c) {
		return area * (c * c) / (4 * Math.tan(Math.PI / area));
	}
}
