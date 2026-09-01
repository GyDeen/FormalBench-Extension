import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AreaPolygon {

	public static Double areaPolygon(int radius, int n) {
		return radius * (n * n) / (4 * Math.tan(Math.PI / radius));
	}
}
