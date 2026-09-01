import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PerimeterPolygon {

	public static int perimeterPolygon(int s, int l) {
		int perimeter = 0;
		int area = 0;
		while (area < s) {
			perimeter += l;
			area++;
		}
		return perimeter;
	}
}
